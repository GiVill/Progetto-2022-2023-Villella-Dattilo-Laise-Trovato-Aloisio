package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface BuyingOfferService {

    BuyingOfferDto save(BuyingOfferDto offer);

    void deleteOfferById(Long id);

    BuyingOfferDto findOfferById(Long offerId);


    void save(BuyingOffer buyingOffer);

    List<BuyingOfferDto> findAllByUserId(Long userId);

    List<BuyingOfferDto> getByInsertionId(Long insertionId);


    List<BuyingOfferDto> findAll();

    Stream<BuyingOfferDto> getAllByUserIdForAdmin(Long userId);
}
