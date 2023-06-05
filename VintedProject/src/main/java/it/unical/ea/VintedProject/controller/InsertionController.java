package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class InsertionController {

    private final BasicInsertionService basicInsertionService;

    @GetMapping("/insertions")
    public ResponseEntity<Page<BasicInsertionDto>> all(@RequestParam("page") int page){
        return ResponseEntity.ok(basicInsertionService.getAllPaged(page));

    }

    @PostMapping("/insertions")
    public ResponseEntity<BasicInsertionDto> addInsertion(@RequestBody @Valid BasicInsertionDto basicInsertionDto) {
        return ResponseEntity.ok(basicInsertionService.saveDto(basicInsertionDto));
    }

    @GetMapping("/insertions/{id}")
    public ResponseEntity<BasicInsertionDto> getInsertionById(@PathVariable Long id) {
        return ResponseEntity.ok(basicInsertionService.getInsertionById(id));
    }

    @DeleteMapping("/insertions/{id}")
    public ResponseEntity<Void> deleteInsertionById(@PathVariable Long id) {
        basicInsertionService.deleteBasicInsertionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/insertions")
    public ResponseEntity<Void> deleteAllInsertionByUserId(@RequestParam("userId") Long userId) {
        basicInsertionService.deleteBasicInsertionById(userId);
        return ResponseEntity.noContent().build();
    }


}
