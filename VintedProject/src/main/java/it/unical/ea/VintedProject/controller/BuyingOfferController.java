package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Offer") //Name displayed on swagger
public class BuyingOfferController {

    private final BuyingOfferService buyingOfferService;
    private final UserService userService;

    @GetMapping("/offers")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<BuyingOfferDto>> all() {
        return ResponseEntity.ok(buyingOfferService.findAll());
    }

    /* TODO: SOLO ADMIN
    @GetMapping("/offers/user/{idUser}")
    public ResponseEntity<Stream<BuyingOfferDto>> allId(@PathVariable("idUser") Long userId) {
        return ResponseEntity.ok(buyingOfferService.getById(userId));
    }
     */

    @GetMapping("/offers/user")
    public ResponseEntity<List<BuyingOfferDto>> getAllByUser() {
        Optional<User> u = userService.findByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null){
            throw new EntityNotFoundException("CACCA");
        }
        return ResponseEntity.ok(buyingOfferService.findAllByUserId(u.get().getId()));
    }

    @GetMapping("/offers/insertion/{insertionId}")
    public ResponseEntity<List<BuyingOfferDto>> allInsertionId(@PathVariable("insertionId") Long insertionId) {
        return ResponseEntity.ok(buyingOfferService.getByInsertionId(insertionId));
    }

    @GetMapping("/offers/admin/{userId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Stream<BuyingOfferDto>> allByUserIdForAdmin(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(buyingOfferService.getAllByUserIdForAdmin(userId));
    }

    @PostMapping("/offers")
    public ResponseEntity<BuyingOfferDto> addBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    @DeleteMapping("/offers/{idOffer}")
    public HttpStatus delete (@PathVariable("idOffer") Long offerId) {
        buyingOfferService.deleteOfferById(offerId);
        return HttpStatus.OK;
    }

}
