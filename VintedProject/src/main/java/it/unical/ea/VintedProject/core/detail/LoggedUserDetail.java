package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Data
public class LoggedUserDetail implements Serializable {

    private static LoggedUserDetail instance;

    public static LoggedUserDetail getInstance() {
        if(instance == null){
            instance = new LoggedUserDetail();
        }
        return instance;
    }

    private LoggedUserDetail(){}

    private String idKeycloak;
    private String email;
    private String username;
    private String sessionId;

    public static void resetInstance(){
        instance = null;
    }
}
