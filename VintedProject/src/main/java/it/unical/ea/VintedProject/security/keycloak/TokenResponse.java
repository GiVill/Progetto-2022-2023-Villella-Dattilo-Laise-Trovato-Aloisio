package it.unical.ea.VintedProject.security.keycloak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String access_token;
    private String token_type;
    // Other fields in the response
}