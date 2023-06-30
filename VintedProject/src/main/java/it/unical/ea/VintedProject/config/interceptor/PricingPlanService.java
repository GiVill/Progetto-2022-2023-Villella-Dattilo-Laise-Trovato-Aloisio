package it.unical.ea.VintedProject.config.interceptor;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
class PricingPlanService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {

        //Qui viene passata una stringa presa dall'header HTTP (vedi RateLimiterInterceptor, il preHandle)
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey); //Il resolvePlanFromApiKey collega ad una stringa un relativo Bucket

        return Bucket.builder()
            .addLimit(pricingPlan.getLimit())
            .build();
    }
}