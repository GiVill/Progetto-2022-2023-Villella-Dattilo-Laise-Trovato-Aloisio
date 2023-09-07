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
                .addPathPatterns("/vintedProject-api/v1/users/{idUser}")
                .addPathPatterns("/vintedProject-api/v1/orders/{orderId}")
                .addPathPatterns("/vintedProject-api/v1/orders")
                .addPathPatterns("/vintedProject-api/v1/offer/orders")
                .addPathPatterns("/vintedProject-api/v1/orders/user/{page}")
                .addPathPatterns("/vintedProject-api/v1/insertions")
                .addPathPatterns("/vintedProject-api/v1/insertions/{insertionId}")
                .addPathPatterns("/vintedProject-api/v1//insertions/order/{orderId}")
                .addPathPatterns("/vintedProject-api/v1/insertions/{idUser}/{page}")
                .addPathPatterns("/vintedProject-api/v1/myInsertions/{page}")
                .addPathPatterns("/vintedProject-api/v1/insertions/title/{title}/{page}")
                .addPathPatterns("/vintedProject-api/v1/insertions/brand/{brand}/{page}")
                .addPathPatterns("/vintedProject-api/v1/insertions/category/{category}/{page}")
                .addPathPatterns("/vintedProject-api/v1/insertions/24h/token/{idInsertion}")
                .addPathPatterns("/vintedProject-api/v1/insertions/year/token/{idInsertion}")
                .addPathPatterns("/vintedProject-api/v1/insertions/private/{token}")
                .addPathPatterns("/vintedProject-api/v1/user/offers")
                .addPathPatterns("/vintedProject-api/v1/offers/insertion/{insertionId}")
                .addPathPatterns("/vintedProject-api/v1/offers")
                .addPathPatterns("/vintedProject-api/v1/offers/accept")
                .addPathPatterns("/vintedProject-api/v1/offers/refuse");

    }
}
