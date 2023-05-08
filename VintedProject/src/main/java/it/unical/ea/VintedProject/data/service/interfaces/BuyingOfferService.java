package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;

import java.util.List;
import java.util.stream.Stream;

public interface BuyingOfferService {

    void save(BuyingOffer offer);

    void deleteOfferById(Long id);

    Stream<BuyingOfferDto> getById(Long id);

    List<BuyingOfferDto> findAll();



}
