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
        //TODO: Aggiungere i giusti endpoint
        registry.addInterceptor(interceptor)
                .addPathPatterns("/vintedProject-api/v1/users")
                .addPathPatterns("/vintedProject-api/v1/users/{idUser}");
    }
}
