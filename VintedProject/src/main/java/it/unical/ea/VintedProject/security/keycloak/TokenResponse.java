package it.unical.ea.VintedProject.security.keycloak;

import it.unical.ea.VintedProject.dto.UserDto;
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
    private UserDto userDto;
}
