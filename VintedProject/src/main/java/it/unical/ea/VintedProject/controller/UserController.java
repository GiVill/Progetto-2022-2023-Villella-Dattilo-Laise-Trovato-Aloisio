package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "User") //Name displayed on swagger
public class UserController {

    private final UserService userService;
    //private LoggedUserDetail loggedUser = LoggedUserDetail.getInstance();

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
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDto>> getAll() {
        // Return a List of UserDto sorted (ascending) by lastname
        // No Throw, No Token Check
        return ResponseEntity.ok(userService.getAllUserDtoSortedByLastnameAscending());
    }


    @GetMapping("/users/{userId}")
    //ADMIN e USER Gli se gli utenti sono pubblici
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable("userId") Long userId){
        // Return UserDto using the id; else user.not.present Exception
        // No Throw
        return ResponseEntity.ok(userService.getUserDtoById(userId));
    }

    @PostMapping("/admin/users")
    // SOLO ADMIN ?
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.saveDto(userDto));
    }

    @PutMapping("/password/{userId}")
    public ResponseEntity<Boolean> updateUserPassword(@PathVariable Long userId,@RequestBody @Valid String newPassword) {
        //TODO loggedUser.getLoggedUserId(userId);
        //if(loggedUser.getLoggedUserId().equals(userId)){
        return ResponseEntity.ok(userService.updateUserPassword(newPassword));
        //} else {
        //TODO: ERRORE PERMESSI
        //    throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        //}
    }
    @DeleteMapping("/admin/users/{userId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> adminDeleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> userDeleteUserById(@PathVariable("userId") Long userId) {
        //loggedUser.checkLoggedUser(userId);
        //if(loggedUser.getLoggedUserId().equals(id)){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
        //} else {
        //TODO: ERRORE PERMESSI
        //    throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        //}
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
