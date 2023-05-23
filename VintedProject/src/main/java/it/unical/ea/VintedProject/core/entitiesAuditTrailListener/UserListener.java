package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;

import it.unical.ea.VintedProject.data.entities.User;
import jakarta.persistence.PostLoad;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserListener {

    @PostLoad
    private void afterLoad(User user){
        log.info("[USER AUDIT] user loaded from database: " + user.getId());
    }
}
