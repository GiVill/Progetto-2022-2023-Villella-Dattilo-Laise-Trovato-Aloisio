package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyingOfferListener {

    @PostLoad
    private void afterLoad(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer loaded with id: " + offer.getId()); }

    @PreUpdate
    private void preUpdate(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " is about to be updated"); }

    @PostUpdate
    private void postUpdate(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " was updated"); }

    //

    @PrePersist
    private void prePersist(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " is about to be entered into the db"); }

    @PostPersist
    private void postPersist(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " was entered into the db"); }

    //

    @PreRemove
    private void preRemove(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " is about to be removed from the db"); }

    @PostRemove
    private void postRemove(BuyingOffer offer){ log.info("[INSERTION AUDIT] offer with id: " + offer.getId() + " was removed from the db"); }

}
