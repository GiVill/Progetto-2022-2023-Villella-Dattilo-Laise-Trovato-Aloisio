package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Image") //Name displayed on swagger
public class ImageController {

    private final ImageService imageService;
    private final LoggedUserMethod loggedUserMethod;


    @GetMapping("/images/{imagePath}")
    public ResponseEntity<Resource> getImageById(@PathVariable("imagePath") String imagePath){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageService.getImage(imagePath));
    }

    @PostMapping("/images/user")
    public ResponseEntity<Boolean> insertUserImage(@RequestBody MultipartFile img){
        return ResponseEntity.ok(imageService.insertUserImage(img));
    }

    @PostMapping("/images/insertion/{insertionId}")
    public ResponseEntity<Boolean> insertInsertionImage(@PathVariable("insertionId") Long insertionId, @RequestBody MultipartFile img){
        return ResponseEntity.ok(imageService.insertInsertionImage(insertionId, img));
    }

    @DeleteMapping("/admin/images/user/{userId}")
    public ResponseEntity<Void> adminDeleteImage(@PathVariable("userId") Long userId){
        imageService.deleteImageUser(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/images/user/{userId}")
    public ResponseEntity<Void> userDeleteImage(@PathVariable("userId") Long userId){
        loggedUserMethod.checkLoggedUser(userId);
        imageService.deleteImageUser(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/images/insertion/{insertionId}")
    public ResponseEntity<Void> adminDeleteImageInsertion(@PathVariable("insertionId") Long insertionId){
        imageService.adminDeleteImageInsertion(insertionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/images/insertion/{insertionId}")
    public ResponseEntity<Void> userImageInsertion(@PathVariable("insertionId") Long insertionId){
        imageService.userDeleteImageInsertion(insertionId);
        return ResponseEntity.noContent().build();

    }
}
