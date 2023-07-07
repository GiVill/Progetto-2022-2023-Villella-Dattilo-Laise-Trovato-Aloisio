package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;

import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.Payment;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentListener {

    @PostLoad
    private void afterLoad(Payment payment){ log.info("[PAYMENT AUDIT] payment loaded with id: " + payment.getId()); }

    @PreUpdate
    private void preUpdate(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " is about to be updated"); }

    @PostUpdate
    private void postUpdate(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " was updated"); }

    //

    @PrePersist
    private void prePersist(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " is about to be entered into the db"); }

    @PostPersist
    private void postPersist(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " was entered into the db"); }

    //

    @PreRemove
    private void preRemove(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " is about to be removed from the db"); }

    @PostRemove
    private void postRemove(Payment payment){ log.info("[PAYMENT AUDIT] payment with id: " + payment.getId() + " was removed from the db"); }

}
