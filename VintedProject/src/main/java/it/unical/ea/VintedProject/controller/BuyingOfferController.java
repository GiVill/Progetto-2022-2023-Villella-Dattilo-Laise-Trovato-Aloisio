package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Offer") //Name displayed on swagger
public class BuyingOfferController {

    private final BuyingOfferService buyingOfferService;

    @GetMapping("/offers")
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<BuyingOfferDto>> all() {
        return ResponseEntity.ok(buyingOfferService.findAll());
    }

    @GetMapping("/offers/{idUser}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Stream<BuyingOfferDto>> allId(@PathVariable("idUser") Long userId) {
        return ResponseEntity.ok(buyingOfferService.getById(userId));
    }

    @GetMapping("/offers/admin/{userId}")
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Stream<BuyingOfferDto>> allByUserIdForAdmin(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(buyingOfferService.getAllByUserIdForAdmin(userId));
    }

    @PostMapping("/offers")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<BuyingOfferDto> addBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    @DeleteMapping("/offers/{idOffer}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public HttpStatus delete (@PathVariable("idOffer") Long offerId) {
        buyingOfferService.deleteOfferById(offerId);
        return HttpStatus.OK;
    }

}
