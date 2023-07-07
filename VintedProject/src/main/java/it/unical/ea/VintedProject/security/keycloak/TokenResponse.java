package it.unical.ea.VintedProject.security.keycloak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    //Fields of the response
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long userId;
}