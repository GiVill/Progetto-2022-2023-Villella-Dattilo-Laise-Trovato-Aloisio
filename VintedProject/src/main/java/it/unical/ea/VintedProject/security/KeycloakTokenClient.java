package it.unical.ea.VintedProject.security;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class KeycloakTokenClient {


    private final MessageLang messageLang;
    private static final String TOKEN_ENDPOINT = "http://localhost:8090/realms/vinted2_0/protocol/openid-connect/token";

    public String getToken(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "vinted-client");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                TOKEN_ENDPOINT,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenResponse tokenResponse = responseEntity.getBody();
            return tokenResponse.getAccess_token();
        } else {
            System.out.println("error while retrieving the token from keycloak!");
            //todo:gestire errore
            throw new RuntimeException("ciao");
        }
    }
}