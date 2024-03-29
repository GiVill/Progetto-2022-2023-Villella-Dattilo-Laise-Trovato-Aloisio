package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import it.unical.ea.VintedProject.security.keycloak.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Auth") //Name displayed on swagger
public class AuthController {

    private final AuthService authService;
    private final KeycloakTokenClient keycloakTokenClient;

    @PostMapping("/sign-up")
    public ResponseEntity<TokenDto> signUp(@RequestBody @Valid NewUserDto newUserDto){
        TokenDto token = keycloakTokenClient.userRegister(newUserDto);
        if(!Objects.equals(token, "ERRORE")  ){

            UserDto userDto = authService.signUp(newUserDto);
            if(userDto != null) {
                token.setUserDto(userDto);
                return ResponseEntity.ok(token);
            }
            return null;
        }
        return  ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginUserDto data){
        return ResponseEntity.ok(authService.doLogin(data));
    }

    @PostMapping("/get-refresh-token")
    public ResponseEntity<TokenDto> getRefreshToken(@RequestBody String Token){
        return ResponseEntity.ok(keycloakTokenClient.getRefreshToken(Token));
    }
}
