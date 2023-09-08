package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
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

    @Autowired
    private HttpServletRequest request;

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
        return ResponseEntity.ok(userService.getAllUserDtoSortedByLastnameAscending());
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.getUserDtoById(userId));
    }

    @PostMapping("/admin/users")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.saveDto(userDto));
    }

    @PutMapping("/password")
    public ResponseEntity<Boolean> updateUserPassword(@RequestBody @Valid String newPassword) {
        return ResponseEntity.ok(userService.updateUserPassword(newPassword));
    }
    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<Void> adminDeleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> userDeleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
