package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import it.unical.ea.VintedProject.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "User") //Name displayed on swagger
public class UserController {

    private final UserService userService;

    @Autowired
    private HttpServletRequest request;

    //TODO: Forse questo @Operation si può eliminare?
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
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.getAllStored());
    }


    @GetMapping("/users/{idUser}")
    public ResponseEntity<UserDto> getById(@PathVariable("idUser") Long id){
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) { return ResponseEntity.ok(userService.saveDto(userDto)); }

    @PutMapping("/password/{idUser}")
    public ResponseEntity<Boolean> updateUserPassword(@PathVariable("idUser") Long id,@RequestBody @Valid String newPassword) { return ResponseEntity.ok(userService.updateUserPassword(id,newPassword));}

    @DeleteMapping("/users/{idUser}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteUserById(@PathVariable("idUser") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: Forse questo si può eliminare?
    public String serviceAFallback(Exception e) {
        return "This is a fallback method for Service user";
    }

    //@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    //@Retry(name = SERVICE_A)
    //@RateLimiter(name = SERVICE_A)

    //@PreAuthorize("hasRole('user')"+"|| hasRole('admin')")
    //@PreAuthorize("hasAnyRole('user','admin')")

}
