package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Offer") //Name displayed on swagger
public class BuyingOfferController {

    private final BuyingOfferService buyingOfferService;
    private final BasicInsertionService insertionService;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;


    @GetMapping("/admin/offers")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<List<BuyingOfferDto>> getAll() {
        // find all BuyingOffers, return all as List.
        // No Throw here!
        return ResponseEntity.ok(buyingOfferService.getAll());
    }

    @GetMapping("/admin/offers/user/{idUser}")
    //TODO: AGGIUNGERE AL SECURITY CON.
    public ResponseEntity<List<BuyingOfferDto>> getAllByUserId(@PathVariable("idUser") Long idUser) {
        // find an Insertion using the ID.
        // No Throw on the find!
        return ResponseEntity.ok(buyingOfferService.getAllByUserId(idUser));
    }

    @GetMapping("/offers/user")
    public ResponseEntity<List<BuyingOfferDto>> getAllByUser() {
        return ResponseEntity.ok(buyingOfferService.getAllByUserId(loggedUserMethod.getLoggedUserId()));
    }

    @GetMapping("/admin/offers/insertion/{insertionId}")
    public ResponseEntity<List<BuyingOfferDto>> adminGetAllByInsertionId(@PathVariable("insertionId") Long insertionId) {
        return ResponseEntity.ok(buyingOfferService.getByInsertionId(insertionId));
    }

    @GetMapping("/offers/insertion/{insertionId}")
    public ResponseEntity<List<BuyingOfferDto>> userGetAllByInsertionId(@PathVariable("insertionId") Long insertionId) {
        BasicInsertion insertion = insertionService.getById(insertionId);
        loggedUserMethod.getLoggedUserId(insertion.getUser().getId());
        return ResponseEntity.ok(buyingOfferService.getByInsertionId(insertionId));
    }

    @GetMapping("/admin/offers/{offerId}")
    //@PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<BuyingOfferDto> getById(@PathVariable("offerId") Long OfferId){
        return ResponseEntity.ok(buyingOfferService.getOfferById(OfferId));
    }

    @PostMapping("/admin/offers")
    public ResponseEntity<BuyingOfferDto> adminAddBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        // Create a BuyingOffer using the Dto
        // No Throw on Save, No Token Check
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    @PostMapping("/offers")
    public ResponseEntity<BuyingOfferDto> userAddBuyingOffer (@RequestBody @Valid BuyingOfferDto offer){
        // Create a BuyingOffer using the Dto
        // No Throw on Save

        loggedUserMethod.getLoggedUserId(offer.getUserId());
        return ResponseEntity.ok(buyingOfferService.save(offer));
    }

    @DeleteMapping("admin/offers/{idOffer}")
    public HttpStatus adminDeleteOffer (@PathVariable("idOffer") Long offerId) {
        // Check the Token, if not ok: THROW Exception.
        // Delete a BuyingOffer using the ID.
        // No Throw on Deletion
        buyingOfferService.deleteOfferById(offerId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/offers")
    public HttpStatus userDeleteOffer (@RequestBody @Valid BuyingOfferDto buyingOfferDto) {
        // Check the Token, if not ok: THROW Exception.
        // Delete a BuyingOffer using the ID.
        // No Throw on Deletion
        buyingOfferService.deleteOfferById(buyingOfferDto.getId());
        return HttpStatus.OK;

    }

    @PutMapping("/offers/accept")
    public HttpStatus acceptOffers (@RequestBody @Valid BuyingOfferDto buyingOfferDto) {
        // Check the Token, if not ok: THROW Exception.
        // Delete a BuyingOffer using the ID.
        // No Throw on Deletion
        buyingOfferService.deleteOfferById(buyingOfferDto.getId());
        return HttpStatus.OK;

    }


    //TODO:PUT

}
