package it.unical.ea.VintedProject.config.interceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
class BucketService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {

        Bandwidth bandwidth = Bandwidth.classic(15, Refill.intervally(10, Duration.ofSeconds(100)));

        return Bucket.builder()
            .addLimit(bandwidth)
            .build();
    }
}