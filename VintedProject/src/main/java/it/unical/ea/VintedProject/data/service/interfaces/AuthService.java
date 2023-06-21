package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.dto.NewUserDto;

public interface AuthService {
    Boolean signin(NewUserDto newUserDto);
}
