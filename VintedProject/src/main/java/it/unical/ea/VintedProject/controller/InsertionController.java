package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import it.unical.ea.VintedProject.dto.enumerated.Brand;
import it.unical.ea.VintedProject.dto.enumerated.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class InsertionController {

    private final BasicInsertionService basicInsertionService;

    @GetMapping("/insertions")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Page<BasicInsertionDto>> all(@RequestParam("page") int page){
        return ResponseEntity.ok(basicInsertionService.getAllPaged(page));

    }

    @PostMapping("/insertions")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<BasicInsertionDto> addInsertion(@RequestBody @Valid BasicInsertionDto basicInsertionDto) {
        return ResponseEntity.ok(basicInsertionService.saveDto(basicInsertionDto));
    }

    @GetMapping("/insertions/{id}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<BasicInsertionDto> getInsertionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(basicInsertionService.getInsertionById(id));
    }

    @DeleteMapping("/insertions/{id}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Void> deleteInsertionById(@PathVariable("id") Long id) {
        basicInsertionService.deleteBasicInsertionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/insertions/{UserId}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Void> deleteAllInsertionByUserId(@RequestParam("userId") Long userId) {
        basicInsertionService.deleteBasicInsertionById(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/insertions/{title}/{page}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Page<BasicInsertionDto>> getByTitle(@PathVariable("title") String title, @PathVariable("page") int page){
         return ResponseEntity.ok(basicInsertionService.getAllByTitleStartWith(title,page));
    }

    @GetMapping("/insertions/{brand}/{page}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Page<BasicInsertionDto>> getByBrand(@PathVariable("brand") Brand brand, @PathVariable("page") int page){
        return ResponseEntity.ok(basicInsertionService.getByBrand(brand,page));
    }

    @GetMapping("/insertions/{category}/{page}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Page<BasicInsertionDto>> getByCategory(@PathVariable("category") Category category, @PathVariable("page") int page){
        return ResponseEntity.ok(basicInsertionService.getByCategory(category,page));
    }

}