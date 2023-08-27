package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.UserServiceImpl;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Optional;

//TODO Sicuri che questa classe serva

@Data
public class LoggedUserDetail implements Serializable {

    private static LoggedUserDetail instance;
    private static UserService userService;

    public static LoggedUserDetail getInstance() {
        if(instance == null){
            instance = new LoggedUserDetail();
        }
        return instance;
    }

    private LoggedUserDetail(){}

    private String idKeycloac;
    private String email;
    private String username;
    private String sessionId;


    public Long getLoggedUserId(){
        Optional<User> user = userService.findByEmail(email);
        return user.map(User::getId).orElse(null);
    }

}
