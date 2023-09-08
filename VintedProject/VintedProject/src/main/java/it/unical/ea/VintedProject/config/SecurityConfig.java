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
                .requestMatchers(HttpMethod.GET,"/v1/admin/users").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET,"/v1/users/{idUser}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/users/{idUser}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/admin/users/{idUser}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/admin/users").hasRole(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/password").hasAnyRole(USER,ADMIN)
                //ORDERS
                .requestMatchers(HttpMethod.GET, "/v1/orders/{orderId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/orders/{userId}/{page}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/orders/email/{userEmail}/{page}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/orders").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/admin/orders").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/orders").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/admin/orders/{orderId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/orders").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/offer/orders").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/orders/user/{page}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/orders/user/{userId}/{page}").hasRole(ADMIN)
                //INSERTION
                .requestMatchers(HttpMethod.GET, "/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/{insertionId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1//insertions/order/{orderId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/{idUser}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/myInsertions/{page}").hasRole(USER)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/title/{title}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/brand/{brand}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/insertions/category/{category}/{page}").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/admin/insertions/{insertionId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/insertions").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/insertions/{userEmail}/{page}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/insertions").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v1/insertions").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/insertions").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/admin/insertions/{insertionId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/v1/admin/insertions").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/24h/token/{idInsertion}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/year/token/{idInsertion}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/insertions/private/{token}").hasAnyRole(USER,ADMIN)
                //OFFERS
                .requestMatchers(HttpMethod.GET, "/v1/admin/offers").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/offers/user/{idUser}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/user/offers").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/offers/insertion/{insertionId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/offers/insertion/{insertionId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.GET, "/v1/admin/offers/{offerId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/admin/offers").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/offers").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/offers/accept").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/offers/refuse").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/admin/offers/{idOffer}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/v1/offers").hasAnyRole(USER,ADMIN)
                //AUTH
                .requestMatchers(HttpMethod.POST, "/v1/sign-up").permitAll()            //OK
                .requestMatchers(HttpMethod.POST, "/v1/login").permitAll()              //OK
                .requestMatchers(HttpMethod.POST, "/v1/get-refresh-token").permitAll()  //OK
                //IMAGE
                .requestMatchers(HttpMethod.GET, "/v1/images/{imagePath}").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/images/user").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST, "/v1/images/insertion/{insertionId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/admin/images/user/{userId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/images/user/{userId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/admin/images/insertion/{insertionId}").hasRole(ADMIN)
                .requestMatchers(HttpMethod.DELETE,"/v1/images/insertion/{insertionId}").hasAnyRole(USER,ADMIN)
                //CHAT
                .requestMatchers(HttpMethod.GET,"/v1/chat/user/{id}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST,"/v1/chat/newchat").hasAnyRole(USER,ADMIN)
                //CHATMESSAGE
                .requestMatchers(HttpMethod.GET,"/v1/chat/message/{chatId}").hasAnyRole(USER,ADMIN)
                .requestMatchers(HttpMethod.POST,"/v1/message/insert").hasAnyRole(USER,ADMIN)

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