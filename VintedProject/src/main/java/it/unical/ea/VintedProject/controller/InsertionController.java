package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.ImageService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Insertion") //Name displayed on swagger
public class InsertionController {

    private final BasicInsertionService basicInsertionService;
    private final ImageService imageService;
    private final LoggedUserMethod loggedUserMethod;

    /*
    getAll
    getInsertionById
    getInsertionByUserId
    getInsertionByUserEmail
    addInsertion
    deleteInsertionById
    deleteInsertionForAdmin
    deleteAllInsertionByUserId
    getByTitle
    getByBrand
    getByCategory
    modifyInsertion
    modifyInsertionById
    generateCapabilities
    getPrivateInsertion
    */

    @GetMapping("/insertions")
    public ResponseEntity<Page<BasicInsertionDto>> userGetAll(@RequestParam("page") int page){
        // find all Insertion, return all as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllByIsPrivateEqualsFalsePaged(page));
    }

    @GetMapping("/insertions/{insertionId}")
    public ResponseEntity<BasicInsertionDto> getInsertionById(@PathVariable("insertionId") Long insertionId) {
        // find Insertion using the ID,
        // if not existent: THROW Exception.
        return ResponseEntity.ok(basicInsertionService.getInsertionByIdAndIsPrivateEqualsFalse(insertionId));
    }

    @GetMapping("/insertions/{idUser}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getInsertionByUserId(@PathVariable("idUser") Long userId, @PathVariable("page") int page){
        // find Insertion using the USER ID, return as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllByUser(userId, page));
    }

    @GetMapping("/myInsertions/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getMyInsertion(@PathVariable("page") int page){
        // find Insertion using the USER ID, return as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllMyInsertions(loggedUserMethod.getLoggedUserId(), page));
    }

    @GetMapping("/insertions/title/{title}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByTitle(@PathVariable("title") String title, @PathVariable("page") int page){
        // find all Insertion (ascending) by Title, return all as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllByTitleStartWith(title,page));
    }

    @GetMapping("/insertions/brand/{brand}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByBrand(@PathVariable("brand") Brand brand, @PathVariable("page") int page){
        // find all Insertion (ascending) by Brand, return all as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getByBrand(brand,page));
    }

    @GetMapping("/insertions/category/{category}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getByCategory(@PathVariable("category") Category category, @PathVariable("page") int page){
        // find all Insertion (ascending) by Category, return all as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getByCategory(category,page));
    }

    @GetMapping("/admin/insertions/{insertionId}")
    public ResponseEntity<BasicInsertionDto> adminGetInsertionById(@PathVariable("insertionId") Long insertionId) {
        // find Insertion using the ID,
        // if not existent: THROW Exception.
        return ResponseEntity.ok(basicInsertionService.getInsertionById(insertionId));
    }

    @GetMapping("/admin/insertions")
    public ResponseEntity<Page<BasicInsertionDto>> adminGetAll(@RequestParam("page") int page){
        // find all Insertion, return all as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllPaged(page));
    }

    @GetMapping("/admin/insertions/{userEmail}/{page}")
    public ResponseEntity<Page<BasicInsertionDto>> getInsertionByUserEmail(@PathVariable("userEmail") String email, @PathVariable("page") int page){
        // find Insertion using the USER EMAIL, return as Paged.
        // No Throw here!
        return ResponseEntity.ok(basicInsertionService.getAllByUserEmail(email, page));
    }

    @PostMapping("/insertions")
    public ResponseEntity<BasicInsertionDto> addInsertion(@RequestPart("insertion") @Valid BasicInsertionDto basicInsertionDto, @RequestPart ("img") MultipartFile img) {
        loggedUserMethod.checkLoggedUser(basicInsertionDto.getUserId());
        BasicInsertionDto newInsertion = basicInsertionService.saveDto(basicInsertionDto);
        if (imageService.insertInsertionImage(newInsertion.getId(),img)) {
            return ResponseEntity.ok(basicInsertionService.getInsertionById(newInsertion.getId()));
        }
        return ResponseEntity.ok(newInsertion);
    }

    @DeleteMapping("/insertions")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> userDeleteInsertion(@RequestBody @Valid BasicInsertionDto insertionDto) {
        // Check the Token, if not ok: THROW Exception.
        // Delete an Insertion using the ID.
        // No Throw on Deletion
        loggedUserMethod.checkLoggedUser(insertionDto.getUserId());
        basicInsertionService.deleteBasicInsertionById(insertionDto.getId());
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/insertions")
    public ResponseEntity<BasicInsertionDto> userModifyInsertion(@RequestBody BasicInsertionDto insertionDto){
        // Check the Token, if not ok: THROW Exception.
        // Modify an already existing Insertion using the Dto
        // No Throw on Save
        return ResponseEntity.ok(basicInsertionService.modifyUserInsertion(insertionDto));
    }

    @DeleteMapping("/admin/insertions/{insertionId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> adminDeleteInsertionByInsertionId(@RequestParam("insertionId") Long insertionId) {
        // Check the Token, if not ok: THROW Exception.
        // Delete an Insertion using the ID.
        // No Throw on Deletion
        basicInsertionService.deleteBasicInsertionById(insertionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/insertions")
    public ResponseEntity<Boolean> adminModifyInsertion(@RequestBody BasicInsertionDto insertionDto){
        // Modify an already existing Insertion using the ID.
        // No Token Check.
        basicInsertionService.modifyInsertion(insertionDto);
        return ResponseEntity.noContent().build();
    }







    @GetMapping("/insertions/24h/token/{idInsertion}")
    public ResponseEntity<String> generate24hToken(@PathVariable("idInsertion") Long insertionId){
        //check if insertion.user.id is logged.user.id
        return ResponseEntity.ok(basicInsertionService.generate24hToken(insertionId));
    }

    @GetMapping("/insertions/year/token/{idInsertion}")
    public ResponseEntity<String> generateYearToken(@PathVariable("idInsertion") Long insertionId){
        //check if insertion.user.id is logged.user.id
        return ResponseEntity.ok(basicInsertionService.generateLongTermToken(insertionId));
    }

    @GetMapping("/insertions/private/{token}")
    public ResponseEntity<BasicInsertionDto> getPrivateInsertion(@PathVariable("token") String token){
        return ResponseEntity.ok(basicInsertionService.getPrivateInsertion(token));
    }


    //TODO aggiornare il security config delle API tutte
    //TODO verificare tutti i messaggi di errore delle eccezioni intendo
    
}
