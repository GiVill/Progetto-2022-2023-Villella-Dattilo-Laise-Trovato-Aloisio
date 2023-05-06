package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyingOfferDao extends JpaRepository<BuyingOffer,Long> {
}
