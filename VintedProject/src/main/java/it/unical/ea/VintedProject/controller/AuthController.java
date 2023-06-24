package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vintedProject-api/v1/")
public class AuthController {

    private final AuthService authService;
    private final KeycloakTokenClient keycloakTokenClient;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid NewUserDto newUserDto){
        //TODO:VALIDARE CAMPI
        //authService.signin(newUserDto);
        System.out.println("ENTRATO => "+ newUserDto.toString());
        return  ResponseEntity.ok(keycloakTokenClient.userRegister(newUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid String email,@RequestBody @Valid String password){
        return ResponseEntity.ok(true);
    }
}
