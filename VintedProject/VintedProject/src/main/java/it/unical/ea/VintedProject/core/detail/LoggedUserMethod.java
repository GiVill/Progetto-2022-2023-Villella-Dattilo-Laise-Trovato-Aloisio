package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.config.JwtAuthConverter;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.BadRequestException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class LoggedUserMethod {

    private final UserDao userDao;
    private final JwtAuthConverter jwtAuthConverter;

    private final MessageLang messageLang;

    public void checkLoggedUser(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());
        userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() ->  new BadRequestException(messageLang.getMessage("access.denied")));
        System.out.println(jwtAuthConverter.getEmail() + " checkLoggedUser");
    }
    public void checkLoggedUser(Long userId){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() ->  new BadRequestException(messageLang.getMessage("access.denied"))));
        System.out.println(jwtAuthConverter.getEmail() + " checkLoggedUser ARGS");

        if(!user.get().getId().equals(userId)){ //|| user.get().getId().equals(userService.getUserById(userId).getId())
            throw new BadRequestException(messageLang.getMessage("access.denied")); //throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }
    }

    public Long getLoggedUserId(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() -> new BadRequestException(messageLang.getMessage("access.denied"))));
        System.out.println(jwtAuthConverter.getEmail() + " getLoggedUserId");

        return user.get().getId();
    }

    public String getLoggedUserIdKeycloak(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() -> new BadRequestException(messageLang.getMessage("access.denied"))));
        System.out.println(jwtAuthConverter.getEmail() + " getLoggedUserId");

        return jwtAuthConverter.getIdKeycloak();
    }

    public Long getLoggedUserId(Long userId){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());
        System.out.println("JWT class: "+ jwtAuthConverter.getIdKeycloak());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() ->  new BadRequestException(messageLang.getMessage("access.denied"))));
        System.out.println(jwtAuthConverter.getEmail() + " getLoggedUserId ARGS");

        if(!user.get().getId().equals(userId)){ // || user.get().getId().equals(userService.getUserById(userId).getId())
            throw new BadRequestException(messageLang.getMessage("access.denied"));
        }

        return user.get().getId();
    }

    public User getEntireLoggedUser(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() ->  new BadRequestException(messageLang.getMessage("access.denied"))));
        System.out.println(jwtAuthConverter.getEmail() + " getEntireLoggedUser");

        return user.get();
    }

}
