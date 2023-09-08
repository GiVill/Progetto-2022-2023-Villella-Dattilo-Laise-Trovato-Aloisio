package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;

import java.util.List;

//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

public interface BuyingOfferService {
    BuyingOfferDto save(BuyingOfferDto offer);

    void deleteOfferById(Long buyingOfferId);

    BuyingOfferDto getOfferById(Long offerId);

    void acceptOffer(BuyingOfferDto buyingOfferDto);


    void save(BuyingOffer buyingOffer);

    List<BuyingOfferDto> getAllByUserId(Long userId);

    List<BuyingOfferDto> getByInsertionId(Long insertionId);

    List<BuyingOfferDto> getAll();

    void refuseOffer(BuyingOfferDto buyingOfferDto);
}
