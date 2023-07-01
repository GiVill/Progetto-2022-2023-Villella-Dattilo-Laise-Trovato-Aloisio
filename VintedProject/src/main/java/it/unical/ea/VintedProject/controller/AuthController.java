package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.UserServiceImpl;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Auth") //Name displayed on swagger
public class AuthController {

    //TODO Scrivere meglio sia sign up

    private final AuthService authService;
    private final KeycloakTokenClient keycloakTokenClient;
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid NewUserDto newUserDto){
        String  token = keycloakTokenClient.userRegister(newUserDto);
        if(!Objects.equals(token, "ERRORE") && authService.signUp(newUserDto) ){
            return  ResponseEntity.ok(token);
        }
        return  ResponseEntity.ok(token);
    }

    //TODO testare ma funziona sicuro
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDto data){
        return ResponseEntity.ok(userService.doLogin(data));
    }
}
