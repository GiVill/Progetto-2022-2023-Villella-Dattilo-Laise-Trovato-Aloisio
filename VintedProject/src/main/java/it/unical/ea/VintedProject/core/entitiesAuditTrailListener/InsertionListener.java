package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;


import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertionListener {

    private BasicInsertionService insertionService;

    @PostLoad
    private void afterLoad(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion loaded with id: " + insertion.getId()); }

    @PreUpdate
    private void preUpdate(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " is about to be updated"); }

    @PostUpdate
    private void postUpdate(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " was updated"); }

    //

    @PrePersist
    private void prePersist(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " is about to be entered into the db"); }

    @PostPersist
    private void postPersist(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " was entered into the db"); }

    //

    @PreRemove
    private void preRemove(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " is about to be removed from the db"); }

    @PostRemove
    private void postRemove(BasicInsertion insertion){ log.info("[INSERTION AUDIT] insertion with id: " + insertion.getId() + " was removed from the db"); }

}
