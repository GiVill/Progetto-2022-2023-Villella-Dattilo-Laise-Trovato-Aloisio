package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.security.keycloak.TokenDto;

//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

public interface AuthService {
    UserDto signUp(NewUserDto newUserDto);

    TokenDto doLogin(LoginUserDto data);


}
