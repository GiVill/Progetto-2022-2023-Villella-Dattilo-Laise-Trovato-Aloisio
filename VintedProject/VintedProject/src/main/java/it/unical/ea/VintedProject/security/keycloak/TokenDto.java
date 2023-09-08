package it.unical.ea.VintedProject.security.keycloak;

import it.unical.ea.VintedProject.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

     String accessToken;
     String refreshToken;
     String tokenType;
     UserDto userDto;
}
