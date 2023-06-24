package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
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
@RequestMapping("/vintedProject-api/v1/")
public class AuthController {

    private final AuthService authService;
    private final KeycloakTokenClient keycloakTokenClient;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid NewUserDto newUserDto){
        String  token = keycloakTokenClient.userRegister(newUserDto);
        if(!Objects.equals(token, "ERRORE") && authService.signUp(newUserDto) ){
            return  ResponseEntity.ok(token);
        }
        return  ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid String email,@RequestBody @Valid String password){
        return ResponseEntity.ok(true);
    }
}
