package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserDao userDao;
    private final KeycloakTokenClient keycloakTokenClient;
    private final PasswordEncoder passwordEncoder;
    private final MessageLang messageLang;

    @Override
    public Boolean signUp(NewUserDto newUserDto) {
        System.out.println(newUserDto);
        User user = modelMapper.map(newUserDto, User.class);
        if(user != null){
            System.out.println("ENTRATO "+user.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String doLogin(LoginUserDto data) {
        Optional<User> u = Optional.ofNullable(userDao.findUserByEmail(data.getEmail()).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"))));
        // NON FARE ALCUN sout DEGLI UTENTI! Data la pesantezza verrà dato un errore col toString() e col  java.lang.StackOverflowError
        // System.out.println(u);
        if (u.isPresent() && passwordEncoder.matches(data.getPassword(), u.get().getPassword())){
            return keycloakTokenClient.getToken(data.getEmail(), data.getPassword());
        }

        throw new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"));
    }

}
