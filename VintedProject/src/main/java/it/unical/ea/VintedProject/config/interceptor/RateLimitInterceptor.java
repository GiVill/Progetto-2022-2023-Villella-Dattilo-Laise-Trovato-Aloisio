package it.unical.ea.VintedProject.config.interceptor;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.exception.ManyRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    private final PricingPlanService pricingPlanService;
    private final MessageLang messageLang;

    private static final boolean PREVENT_ATTACK_DDOS = true; //TODO: Andrebbe messa a true

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String apiKey = request.getHeader("X-api-key"); //prende il campo "X-api-key" dalla richiesta

        if (apiKey == null || apiKey.isEmpty()) { // SE VUOTA entra nell'if ALTRIMENTI VIENE PASSATA COSì COM'è STATA INVIATA NELL'HEADER
            if (PREVENT_ATTACK_DDOS) {
                apiKey = "PREVENT"; //Si imposta un bucket restrittivo (vedi la classe PricingPlan). PREVENT è il più restrittivo
            }
            else {
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                apiKey = "FREE-" + ipAddress; //Si imposta un bucket restrittivo (vedi la classe PricingPlan). FREE è un pò meno restrittivo di PREVENT
            }
        }

        /* La stringa apiKey verrà passata al resolveBucket il quale tramite una funzione di PricingPlan (che collega ogni singolo Bucket con una relativa stringa),
           risolverà il Bucket. I Bucket sono definiti in PricingPlan ed anche la Bucket-Stringa è in PricingPlan!  */
        Bucket tokenBucket = pricingPlanService.resolveBucket(apiKey);

        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1); //Viene consumato 1 sul Bucket: il contatore decrementa

        if (probe.isConsumed()) {
            //Nell'Header viene aggiunto il campo X-Rate-Limit-Remaining con le richieste ancora disponibili prima di raggiungere il limite
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        }
        else { //Se il bucket ha raggiunto il limite:
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;

            /* Nell'Header viene aggiunto il campo X-Rate-Limit-Retry-After-Seconds con il valore espresso in secondi rappresentati l'attesa prima che il bucket
               venga "svuotato" e si possa riprendere a comunicare con le API */
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            throw new ManyRequestException(messageLang.getMessage("exhausted.api"));
        }
    }
}