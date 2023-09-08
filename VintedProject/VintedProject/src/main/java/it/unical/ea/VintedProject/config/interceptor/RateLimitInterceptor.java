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

    private final BucketService bucketService;
    private final MessageLang messageLang;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String apiKey = request.getHeader("X-api-key"); //prende il campo "X-api-key" dalla richiesta

        if (apiKey == null || apiKey.isEmpty()) { // SE VUOTA entra nell'if ALTRIMENTI VIENE PASSATA COSì COM'è STATA INVIATA NELL'HEADER

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            apiKey = "NORMAL-" + ipAddress;

        }

        Bucket tokenBucket = bucketService.resolveBucket(apiKey);
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