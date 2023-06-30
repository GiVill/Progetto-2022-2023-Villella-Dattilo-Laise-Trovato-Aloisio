package it.unical.ea.VintedProject.core.detail;

import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.enumeration.Role;


public class LoggedUserDetail implements UserLogged {

    //private static final long serialVersionUID = 1;

    private String username;

    private String email;

    private String password;

    private Role auth;

    public LoggedUserDetail(User user){
        this.username = user.getNickName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.auth = user.getRole();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    public Role getRole(){
        return auth;
    }


}
