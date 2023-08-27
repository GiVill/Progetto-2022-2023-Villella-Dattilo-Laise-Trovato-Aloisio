package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
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
    private final BasicInsertionService insertionService;
    private LoggedUserDetail loggedUser = LoggedUserDetail.getInstance();

    @GetMapping("/admin/offers")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<BuyingOfferDto>> findAllBuyingOffers() {
        return ResponseEntity.ok(buyingOfferService.findAll());
    }

    @GetMapping("/admin/offers/user/{idUser}")
    //@PreAuthorize("hasAnyRole('admin')")
    //TODO: AGGIUNGERE AL SECURITY CON.
    public ResponseEntity<List<BuyingOfferDto>> getAllByUserId(@PathVariable("idUser") Long userId) {
        return ResponseEntity.ok(buyingOfferService.findAllByUserId(userId));
    }

    @GetMapping("/offers/user")
    public ResponseEntity<List<BuyingOfferDto>> getAllByUser() {
        if(loggedUser.getLoggedUserId() != null) {
            return ResponseEntity.ok(buyingOfferService.findAllByUserId(loggedUser.getLoggedUserId()));
        } else {
            //TODO: ERRORE PERMESSI
            throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }

    }

    @GetMapping("/admin/offers/insertion/{insertionId}")
    public ResponseEntity<List<BuyingOfferDto>> adminGetAllByInsertionId(@PathVariable("insertionId") Long insertionId) {
        return ResponseEntity.ok(buyingOfferService.getByInsertionId(insertionId));
    }

    @GetMapping("/offers/insertion/{insertionId}")
    public ResponseEntity<List<BuyingOfferDto>> userGetAllByInsertionId(@PathVariable("insertionId") Long insertionId) {
        BasicInsertion insertion = insertionService.findById(insertionId);
        if(insertion.getUser().getId().equals(loggedUser.getLoggedUserId())){
            return ResponseEntity.ok(buyingOfferService.getByInsertionId(insertionId));
        }else {
            //TODO: ERRORE PERMESSI
            throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }
    }

    @GetMapping("/admin/offers/{offerId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<BuyingOfferDto> findById(@PathVariable("offerId") Long OfferId){
        return ResponseEntity.ok(buyingOfferService.findOfferById(OfferId));
    }

    @PostMapping("/admin/offers")
    public ResponseEntity<BuyingOfferDto> adminAddBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    @PostMapping("/offers")
    public ResponseEntity<BuyingOfferDto> userAddBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        if(loggedUser.getLoggedUserId().equals(offer.getUserId())){
            return ResponseEntity.ok(buyingOfferService.save(offer));
        } else {
            //TODO: ERRORE PERMESSI
            throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }
    }

    @DeleteMapping("admin/offers/{idOffer}")
    public HttpStatus adminDeleteOffer (@PathVariable("idOffer") Long offerId) {
        buyingOfferService.deleteOfferById(offerId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/offers")
    public HttpStatus userDeleteOffer (@RequestBody @Valid BuyingOfferDto buyingOfferDto) {
        if(loggedUser.getLoggedUserId().equals(buyingOfferDto.getUserId())){
            buyingOfferService.deleteOfferById(buyingOfferDto.getId());
            return HttpStatus.OK;
        } else {
            //TODO: ERRORE PERMESSI
            throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }
    }


    //TODO:PUT

}
