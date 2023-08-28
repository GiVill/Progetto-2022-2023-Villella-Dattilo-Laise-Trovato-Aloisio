package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.config.JwtAuthConverter;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class LoggedUserMethod {

    private final UserDao userDao;
    private final JwtAuthConverter jwtAuthConverter;

    public void checkLoggedUser(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());
        userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail()).orElseThrow(() -> new EntityNotFoundException("checkLoggedUser"));
        System.out.println(LoggedUserDetail.getInstance().getEmail() + " checkLoggedUser");
        //LoggedUserDetail.resetInstance();
    }
    public void checkLoggedUser(Long userId){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail()).orElseThrow(() -> new EntityNotFoundException("checkLoggedUser ARGS")));
        System.out.println(LoggedUserDetail.getInstance().getEmail() + " checkLoggedUser ARGS");
        //LoggedUserDetail.resetInstance();

        if(user.get().getId().equals(userDao.findById(userId).get().getId())){ //|| user.get().getId().equals(userService.getUserById(userId).getId())
            throw new RuntimeException("No user logged or user not valid"); //throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }
    }

    public Long getLoggedUserId(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() -> new EntityNotFoundException("getLoggedUserId")));
        System.out.println(LoggedUserDetail.getInstance().getEmail() + " getLoggedUserId");
        //LoggedUserDetail.resetInstance();

        //return user.map(User::getId).orElse(null);
        return user.get().getId();
    }

    public Long getLoggedUserId(Long userId){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() -> new EntityNotFoundException("getLoggedUserId ARGS")));
        System.out.println(LoggedUserDetail.getInstance().getEmail() + " getLoggedUserId ARGS");
        //LoggedUserDetail.resetInstance();
        if(user.get().getId().equals(userDao.findById(userId).get().getId())){ // || user.get().getId().equals(userService.getUserById(userId).getId())
            throw new RuntimeException("No user logged or user not valid"); //throw new RuntimeException("NON HAI I PERMESSI; (DEVI LOGGARTI)");
        }

        return user.get().getId();
    }

    public User getEntireLoggedUser(){
        System.out.println("JWT class: "+ jwtAuthConverter.getEmail());

        Optional<User> user = Optional.ofNullable(userDao.findUserByEmail(jwtAuthConverter.getEmail()).orElseThrow(() -> new EntityNotFoundException("getEntireLoggedUser")));
        System.out.println(LoggedUserDetail.getInstance().getEmail() + " getEntireLoggedUser");
        //LoggedUserDetail.resetInstance();

        return user.get();
    }

}
