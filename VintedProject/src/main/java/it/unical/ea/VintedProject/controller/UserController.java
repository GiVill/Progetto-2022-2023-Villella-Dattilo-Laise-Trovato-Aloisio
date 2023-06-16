package it.unical.ea.VintedProject.controller;

import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.service.UserServiceImpl;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final MessageLang messageLang;

    private final UserService userService;

    @GetMapping("/users/swagger")
    @PreAuthorize("permitAll()")
    public void swagger() {
        System.out.println("Swagger documentation is running at: http://localhost:8010/swagger-ui/index.html");
    }


    @Operation(
            description = "Get endpoint for user",
            summary = "this is the list of user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/users")
    //@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.getAllStored());
    }


    @GetMapping("/users/{idUser}")
    @PreAuthorize("hasAnyRole('user','admin')")
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

    public String serviceAFallback(Exception e) {
        return "This is a fallback method for Service user";
    }



    //@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    //@Retry(name = SERVICE_A)
    //@RateLimiter(name = SERVICE_A)

    //@PreAuthorize("hasRole('user')"+"|| hasRole('admin')")
    //@PreAuthorize("hasAnyRole('user','admin')")

}
