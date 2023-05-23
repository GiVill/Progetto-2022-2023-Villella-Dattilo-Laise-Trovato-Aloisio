package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.service.UserServiceImpl;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final MessageLang messageLang;


    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.getAllStored());
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<UserDto> getById(@PathVariable("idUser") Long id){
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/users/{idUser}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}
