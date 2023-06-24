package it.unical.ea.VintedProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/v2/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/actuator/health",
                        "vintedProject-api/v1/**"
                ).permitAll()

                //USERS
                .requestMatchers(HttpMethod.GET,"/vintedProject-api/v1/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/vintedProject-api/v1/users/{idUser}").permitAll()
                .requestMatchers(HttpMethod.GET,"/vintedProject-api/v1/users/insertions/{idUser}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/vintedProject-api/v1/users/{idUser}").permitAll() //.hasAnyRole(ADMIN,BASIC)
                //PAYMENT
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/payments").permitAll()
                .requestMatchers(HttpMethod.GET,"/vintedProject-api/v1/payments/{idPayment}").permitAll()
                .requestMatchers(HttpMethod.POST, "/vintedProject-api/v1/payments").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/vintedProject-api/v1/payments/{idPayment}").permitAll() //.hasRole(ADMIN)
                //ORDERS
                .requestMatchers(HttpMethod.POST, "/vintedProject-api/v1/orders").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/orders/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/orders").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/vintedProject-api/v1/orders/{id}").permitAll()
                //INSERTION
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.POST, "/vintedProject-api/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/insertions/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/vintedProject-api/v1/insertions/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/vintedProject-api/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/insertions/{title}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/insertions/{brand}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/insertions/{category}/{page}").permitAll()
                //OFFERS
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/offers").permitAll()
                .requestMatchers(HttpMethod.GET, "/vintedProject-api/v1/offers/{idUser}").permitAll()
                .requestMatchers(HttpMethod.POST, "/vintedProject-api/v1/offers").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/vintedProject-api/v1/offers/{idOffer}").permitAll()
                //AUTH
                .requestMatchers(HttpMethod.POST, "/vintedProject-api/v1/sign-up").permitAll()
                .anyRequest()
                        .authenticated()
        ;

        http.cors();

        http
                .oauth2ResourceServer()
                    .jwt()
                        .jwtAuthenticationConverter(jwtAuthConverter);

        http
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS);

        http.headers().httpStrictTransportSecurity()
                .maxAgeInSeconds(0)
                .includeSubDomains(true);


        return http.build();
    }
}
