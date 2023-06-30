package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class BuyingOfferController {

    private final BuyingOfferService buyingOfferService;

    @GetMapping("/offers")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<List<BuyingOfferDto>> all() {
        return ResponseEntity.ok(buyingOfferService.findAll());
    }

    @GetMapping("/offers/{idUser}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Stream<BuyingOfferDto>> allId(@PathVariable("idUser") Long id) {
        return ResponseEntity.ok(buyingOfferService.getById(id));
    }

    @PostMapping("/offers")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<BuyingOfferDto> addBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    /*
    @PostMapping("/students")
    public ResponseEntity<StudentBasicDto> add(@RequestBody @Valid StudentDto student) {
        return ResponseEntity.ok(studentService.save(student));
      }
     */

    @DeleteMapping("/offers/{idOffer}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public HttpStatus delete (@PathVariable("idOffer") Long id) {
        buyingOfferService.deleteOfferById(id);
        return HttpStatus.OK;
    }

}
