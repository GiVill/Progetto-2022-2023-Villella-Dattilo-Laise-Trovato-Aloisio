package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping("/images/{imagePath}")
    public ResponseEntity<Resource> getImageById(@PathVariable("imagePath") String imagePath){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageService.getImage(imagePath));
    }

    @PostMapping("/images/user/{userId}")
    public ResponseEntity<Boolean> insertUserImage(@PathVariable("userId") Long userId, @RequestBody MultipartFile img){
        return ResponseEntity.ok(imageService.insertUserImage(userId, img));
    }

    @PostMapping("/images/insertion/{insertionId}")
    public ResponseEntity<Boolean> insertInsertionImage(@PathVariable("insertionId") Long insertionId, @RequestBody MultipartFile img){
        return ResponseEntity.ok(imageService.insertInsertionImage(insertionId, img));
    }
}
