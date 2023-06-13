package it.unical.ea.VintedProject.controller;

import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.service.UserServiceImpl;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final MessageLang messageLang;


    private final UserService userService;

    @GetMapping("/users/swagger")
    @PreAuthorize("permitAll()")
    public void swagger() {
        System.out.println("Swagger documentation is running at: http://localhost:8010/swagger-ui/index.html");
    }


    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.getAllStored());
    }

    //@PreAuthorize("hasRole('user')"+"|| hasRole('admin')")
    //@PreAuthorize("hasAnyRole('user','admin')")
    @GetMapping("/users/{idUser}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<UserDto> getById(@PathVariable("idUser") Long id){
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/users/{idUser}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteUserById(@PathVariable("idUser") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}
