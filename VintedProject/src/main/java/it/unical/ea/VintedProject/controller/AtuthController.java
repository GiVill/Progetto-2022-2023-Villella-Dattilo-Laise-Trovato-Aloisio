package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.security.KeycloakTokenClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vintedProject-api/v1/")
@Validated
public class AtuthController {

    private final AuthService authService;
    private final KeycloakTokenClient keycloakTokenClient;
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid NewUserDto newUserDto){
        //TODO:VALIDARE CAMPI
        //authService.signin(newUserDto);
        return  ResponseEntity.ok(keycloakTokenClient.userRegister(newUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid String email,@RequestBody @Valid String password){
        return ResponseEntity.ok(true);
    }
}
