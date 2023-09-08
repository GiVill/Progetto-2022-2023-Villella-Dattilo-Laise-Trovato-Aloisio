package it.unical.ea.VintedProject.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final RateLimitInterceptor interceptor;

    //list of endpoint with rate limiter (Bucket)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/v1/users/{idUser}")
                .addPathPatterns("/v1/orders/{orderId}")
                .addPathPatterns("/v1/orders")
                .addPathPatterns("/v1/offer/orders")
                .addPathPatterns("/v1/orders/user/{page}")
                .addPathPatterns("/v1/insertions")
                .addPathPatterns("/v1/insertions/{insertionId}")
                .addPathPatterns("/v1/insertions/order/{orderId}")
                .addPathPatterns("/v1/insertions/{idUser}/{page}")
                .addPathPatterns("/v1/myInsertions/{page}")
                .addPathPatterns("/v1/insertions/title/{title}/{page}")
                .addPathPatterns("/v1/insertions/brand/{brand}/{page}")
                .addPathPatterns("/v1/insertions/category/{category}/{page}")
                .addPathPatterns("/v1/insertions/24h/token/{idInsertion}")
                .addPathPatterns("/v1/insertions/year/token/{idInsertion}")
                .addPathPatterns("/v1/insertions/private/{token}")
                .addPathPatterns("/v1/user/offers")
                .addPathPatterns("/v1/offers/insertion/{insertionId}")
                .addPathPatterns("/v1/offers")
                .addPathPatterns("/v1/offers/accept")
                .addPathPatterns("/v1/offers/refuse");

    }
}
