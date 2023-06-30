package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;

import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderListener {

    private OrderService orderService;

    @PostLoad
    private void afterLoad(Order order){
        log.info("[ORDER AUDIT] order loaded from db: " + order.getId());
    }

    @PrePersist
    private void prePersist(Order order){ log.info("[ORDER AUDIT] order with id: " + order.getId() + " is about to be entered on db"); }

    @PostPersist
    private void postPersist(Order order){ log.info("[ORDER AUDIT] order with id: " + order.getId() + " was entered into the db"); }

    //

    @PreRemove
    private void preRemove(Order order){ log.info("[ORDER AUDIT] order with id: " + order.getId() + " is about to be removed from the db"); }

    @PostRemove
    private void postRemove(Order order){ log.info("[ORDER AUDIT] order with id: " + order.getId() + " was removed from the db"); }
}
