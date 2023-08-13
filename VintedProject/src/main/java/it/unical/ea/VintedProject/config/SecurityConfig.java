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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .requestMatchers(HttpMethod.GET,"/v1/users").permitAll()                //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/users/{idUser}").permitAll()       //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/users/{idUser}").permitAll()   //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/users").permitAll()              //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/password/{idUser}").permitAll()   //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/nickname/{idUser}").permitAll()   //.hasAnyRole(USER,ADMIN)
                //PAYMENT
                .requestMatchers(HttpMethod.GET, "/v1/payments").permitAll()                        //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/payments/{idPayment}").permitAll()             //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/payments").permitAll()                       //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/payments/{idPayment}/{idUser}").permitAll() //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/payment/admin/{paymentId}").permitAll()     //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/payments/user/{userId}/{page}").permitAll()    //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/payments/admin/{userId}/{page}").permitAll()   //.hasRole(ADMIN)
                //ORDERS
                .requestMatchers(HttpMethod.POST, "/v1/orders").permitAll()                     //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders/{orderId}").permitAll()            //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders/admin/id/{userId}/{page}").permitAll()      //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders/admin/email/{userEmail}/{page}").permitAll()   //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders").permitAll()                      //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/orders/{orderId}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/orders/admin/{orderId}").permitAll()   //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders/user/{userId}/{page}").permitAll() //.hasAnyRole(USER,ADMIN)
                //INSERTION
                .requestMatchers(HttpMethod.GET, "/v1/insertions").permitAll()                              //?
                .requestMatchers(HttpMethod.POST, "/v1/insertions").permitAll()                             //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/{insertionId}").permitAll()                //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/insertions/user/id/{idUser}/{page}").permitAll()       //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/insertions/user/email/{userEmail}/{page}").permitAll() //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions/{insertionId}").permitAll()             //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions/admin/{insertionId}").permitAll()       //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions/{userId}").permitAll()                  //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/title/{title}/{page}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/brand/{brand}/{page}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/category/{category}/{page}").permitAll()   //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/insertions/{insertionId}").permitAll()                //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/insertions/admin/{insertionId}").permitAll()          //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/insertions/token/{idInsertion}").permitAll()           //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/private/{token}").permitAll()              //.hasAnyRole(USER,ADMIN)
                //OFFERS
                .requestMatchers(HttpMethod.GET, "/v1/offers").permitAll()                  //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/offers/user/{idUser}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/offers/insertion/{insertionId}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/offers/admin/{userId}").permitAll()   //.hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/offers").permitAll()                 //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/offers/{idOffer}").permitAll()     //.hasAnyRole(USER,ADMIN)
                //AUTH
                .requestMatchers(HttpMethod.POST, "/v1/sign-up").permitAll()            //OK
                .requestMatchers(HttpMethod.POST, "/v1/login").permitAll()              //OK
                .requestMatchers(HttpMethod.POST, "/v1/get-refresh-token").permitAll()  //OK
                //IMAGE
                .requestMatchers(HttpMethod.GET, "/v1/images/{imagePath}").permitAll()                  //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/images/user").permitAll()                        //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/images/insertion/{insertionId}").permitAll()     //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/images/user/{insertionId}").permitAll()         //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/images/insertion/{insertionId}").permitAll()    //.hasAnyRole(USER,ADMIN)
                //CHAT
                .requestMatchers(HttpMethod.GET,"/v1/chat/user/{id}").permitAll()           //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/chat/message/{id}/{id2}").permitAll()  //.hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST,"/v1/chat/insert").permitAll()             //.hasAnyRole(USER,ADMIN)

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
