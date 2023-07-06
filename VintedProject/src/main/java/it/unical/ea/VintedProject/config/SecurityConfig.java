package it.unical.ea.VintedProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    private final String ADMIN = "admin";
    private final String USER = "user";

    //Password encoder with BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }

    //list of all endpoint with relative permission
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

                //.hasRole("user")
                //.hasRole(ADMIN)
                //.hasAnyRole(ADMIN,USER)


                //USERS
                .requestMatchers(HttpMethod.GET,"/v1/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/v1/users/{idUser}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/users/{idUser}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/v1/update-users-password/{idUser}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/v1/update-users-nickname/{idUser}").permitAll()
                //PAYMENT
                .requestMatchers(HttpMethod.GET, "/v1/payments").permitAll()
                .requestMatchers(HttpMethod.GET,"/v1/payments/{idPayment}").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/payments").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/v1/payments/{idPayment}").permitAll()
                .requestMatchers(HttpMethod.GET,"/v1/payments/user/{userId}/{page}").permitAll()
                //ORDERS
                .requestMatchers(HttpMethod.POST, "/v1/orders").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/orders/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/orders").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/orders/{id}").permitAll()
                //INSERTION
                .requestMatchers(HttpMethod.GET, "/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/{id}").permitAll()
                .requestMatchers(HttpMethod.GET,"/v1/insertions/user/{idUser}/{page}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/title/{title}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/brand/{brand}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/category/{category}/{page}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/v1/insertions/{InsertionId}").permitAll()
                .requestMatchers(HttpMethod.GET,"/v1/insertions/isNormal/{page}").permitAll()
                //OFFERS
                .requestMatchers(HttpMethod.GET, "/v1/offers").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/offers/{idUser}").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/offers").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/offers/{idOffer}").permitAll()
                //AUTH
                .requestMatchers(HttpMethod.POST, "/v1/sign-up").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/get-refresh-token").permitAll()
                .anyRequest()
                        .authenticated()
        ;

        http.cors();

        http
                .oauth2ResourceServer()
                    .jwt()
                        .jwtAuthenticationConverter(jwtAuthConverter);

        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.headers().httpStrictTransportSecurity()
                .maxAgeInSeconds(0)
                .includeSubDomains(true);

        return http.build();
    }
}
