package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "User") //Name displayed on swagger
public class UserController {

    private final UserService userService;

    //TODO: Forse questa GET si può eliminare?
    @GetMapping("/swagger")
    @PreAuthorize("permitAll()")
    public void swagger() {
        System.out.println("Swagger documentation is running at: http://localhost:8010/swagger-ui/index.html");
    }

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
    //@CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(userService.getAllStored());
    }


    @GetMapping("/users/{idUser}")
    //@PreAuthorize("permitAll()")//hasAnyRole('user','admin')
    public ResponseEntity<UserDto> getById(@PathVariable("idUser") Long id){
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/users")
    //@PreAuthorize("permitAll()")//hasAnyRole('user','admin')
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) { return ResponseEntity.ok(userService.saveDto(userDto)); }

    @PutMapping("/update-users-password/{idUser}")
    public ResponseEntity<Boolean> updateUserPassword(@PathVariable("idUser") Long id, @RequestBody @Valid String newPassword) { return ResponseEntity.ok(userService.updateUserPassword(id, newPassword));}

    @PutMapping("/update-users-nickname/{idUser}")
    public ResponseEntity<Boolean> updateUserNickname(@PathVariable("idUser") Long id, @RequestBody @Valid String newNickname) { return ResponseEntity.ok(userService.updateUserNickname(id, newNickname));}

    @DeleteMapping("/users/{idUser}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteUserById(@PathVariable("idUser") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/uploadImage/{idUser}")
    public ResponseEntity<Boolean> uploadUserImage(@PathVariable("idUser") Long id, @RequestBody @Valid MultipartFile img){
        System.out.println(img.toString());
        return ResponseEntity.ok(userService.uploadUserImage(id,img));
    }

    /*
    IMMAGINI SU FILE SISTEM MA NON VA !
    @PostMapping("/users/uploadImage/{idUser}")
    public ResponseEntity<Boolean> uploadUserImage(@PathVariable("idUser") Long id, @RequestBody @Valid MultipartFile img){
        System.out.println(img.toString());
        return ResponseEntity.ok(userService.uploadUserImage(id,img));
    }

    @GetMapping("/users/uploadImage/{path}")
    public ResponseEntity<?> uploadUserImage(@PathVariable("path") String path){
        Resource resource = userService.loadUserImage(path);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type based on your image type
                .body(resource);
    }
     */

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
