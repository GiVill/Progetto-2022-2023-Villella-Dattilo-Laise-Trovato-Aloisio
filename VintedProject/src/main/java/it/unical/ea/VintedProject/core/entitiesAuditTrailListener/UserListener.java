package it.unical.ea.VintedProject.core.entitiesAuditTrailListener;

import it.unical.ea.VintedProject.data.entities.User;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserListener{

    //private UserService userService;

    @PostLoad
    private void afterLoad(User user){ log.info("[USER AUDIT] user loaded from database: " + user.getId()); }

    @PostUpdate
    private void postUpdate(User user){
        log.info("[USER AUDIT] user with id: " + user.getId() + " was updated");
    }

    //

    @PreUpdate
    private void preUpdate(User user){ log.info("[USER AUDIT] user with id: " + user.getId() + " is about to be updated"); }

    @PrePersist
    private void prePersist(User user){ log.info("[USER AUDIT] user with id: " + user.getId() + " is about to be entered into the db"); }

    @PostPersist
    private void postPersist(User user){ log.info("[USER AUDIT] user with id: " + user.getId() + " was entered into the db"); }

    //

    @PreRemove
    private void preRemove(User user){ log.info("[USER AUDIT] user with id: " + user.getId() + " is about to be removed from the db"); }

    @PostRemove
    private void postRemove(User user){ log.info("[USER AUDIT] user with id: " + user.getId() + " was removed from the db"); }

/*

//TODO: Ma questo che ci fa qui?

    public LoggedUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByNickName(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

        LoggedUserDetail loggedUserDetail = new LoggedUserDetail(user);
        log.info(loggedUserDetail.toString());
        return loggedUserDetail;
    }
*/
}
