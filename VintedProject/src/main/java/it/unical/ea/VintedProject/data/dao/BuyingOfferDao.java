package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyingOfferDao extends JpaRepository<BuyingOffer,Long> {

    Optional<BuyingOffer> findById(Long id);
    List<BuyingOffer> findByInsertionId(Long aLong);

    List<BuyingOffer> findAllByUserId(Long userId);

    //trova le offerte maggiori uguali a quella passata
    //Return a list of ALL Offers with a price greater than AND equal to an amount
    List<BuyingOffer> findAllByPriceGreaterThanEqual(float price);

    //trova le offerte minore ugali a quella passata
    //Return a list of ALL Offers with a price lower than AND equal to an amount
    List<BuyingOffer> findAllByPriceLessThanEqual(float price);

}
