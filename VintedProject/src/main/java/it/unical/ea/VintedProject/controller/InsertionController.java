package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.JwtAuthConverter;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.ImageService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Insertion") //Name displayed on swagger
public class InsertionController {

    private final BasicInsertionService basicInsertionService;
    private final ImageService imageService;

    @GetMapping("/insertions")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Page<BasicInsertionDto>> all(@RequestParam("page") int page){
        return ResponseEntity.ok(basicInsertionService.getAllPaged(page));
    }

    @PostMapping("/insertions")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<BasicInsertionDto> addInsertion(@RequestPart("insertion") @Valid BasicInsertionDto basicInsertionDto, @RequestPart("img") MultipartFile img) {
        BasicInsertionDto newInsertion = basicInsertionService.saveDto(basicInsertionDto);
        if (imageService.insertInsertionImage(newInsertion.getId(),img)) {
            return ResponseEntity.ok(basicInsertionService.getInsertionById(newInsertion.getId()));
        }
        return ResponseEntity.ok(newInsertion);
    }

    @GetMapping("/insertions/{insertionId}")
    public ResponseEntity<BasicInsertionDto> getInsertionById(@PathVariable("insertionId") Long insertionId) {
        return ResponseEntity.ok(basicInsertionService.getInsertionById(insertionId));
    }

    @GetMapping("/insertions/user/{idUser}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getInsertionByUserId(@PathVariable("idUser") Long insertionId, @PathVariable("page") int page){
        return ResponseEntity.ok(basicInsertionService.findAllByUser(insertionId, page));
    }

    @DeleteMapping("/insertions/{insertionId}")
    public ResponseEntity<Void> deleteInsertionById(@PathVariable("insertionId") Long insertionId) {
        basicInsertionService.deleteBasicInsertionById(insertionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/insertions/admin/{insertionId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> deleteInsertionForAdmin(@PathVariable("insertionId") Long insertionId){
        basicInsertionService.deleteBasicInsertionForAdmin(insertionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/insertions/{userId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> deleteAllInsertionByUserId(@RequestParam("userId") Long userId) {
        basicInsertionService.deleteBasicInsertionById(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/insertions/title/{title}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByTitle(@PathVariable("title") String title, @PathVariable("page") int page){
         return ResponseEntity.ok(basicInsertionService.getAllByTitleStartWith(title,page));
    }

    @GetMapping("/insertions/brand/{brand}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByBrand(@PathVariable("brand") Brand brand, @PathVariable("page") int page){
        return ResponseEntity.ok(basicInsertionService.getByBrand(brand,page));
    }

    @GetMapping("/insertions/category/{category}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByCategory(@PathVariable("category") Category category, @PathVariable("page") int page){
        return ResponseEntity.ok(basicInsertionService.getByCategory(category,page));
    }

    @PutMapping("/insertions/{insertionId}")
    public ResponseEntity<Boolean> modifyInsertionById(@PathVariable("insertionId") Long insertionId, String title, Float price, String description){
        return ResponseEntity.ok(basicInsertionService.modifyById(insertionId,title,price,description));
    }

    @PutMapping("/insertions/admin/{insertionId}")
    //@PreAuthorize("hasAnyRole('admin')")
    //TODO:RICONTROLLARE QUESTI CONTROLLER
    public ResponseEntity<Boolean> modifyInsertionByIdForAdmin(@PathVariable("insertionId") Long insertionId, String title, Float price, String description){
        return ResponseEntity.ok(basicInsertionService.modifyByIdForAdmin(insertionId,title,price,description));
    }

    //TODO solo utente su proprie inserzioni??
    @GetMapping("/insertions/token/{idInsertion}")
    public ResponseEntity<String> generateCapabilities(@PathVariable("idInsertion") Long insertionId){
        return ResponseEntity.ok(basicInsertionService.generateToken(insertionId));
    }

    @GetMapping("/insertions/private/{token}")
    public ResponseEntity<BasicInsertionDto> getPrivateInsertion(@PathVariable("token") String token){
        return ResponseEntity.ok(basicInsertionService.getPrivateInsertion(token));
    }

    //TODO aggiornare il security config delle API tutte
    //TODO verificare tutti i messaggi di errore delle eccezioni intendo
    
}
