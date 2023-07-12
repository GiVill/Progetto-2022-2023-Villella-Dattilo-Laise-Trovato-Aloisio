package it.unical.ea.VintedProject.data.service;

import com.nimbusds.jose.JOSEException;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.security.TokenStore;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import it.unical.ea.VintedProject.security.keycloak.TokenResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    public UserDto signUp(NewUserDto newUserDto) {
        System.out.println(newUserDto);
        User user = modelMapper.map(newUserDto, User.class);
        if(user != null){
            System.out.println("ENTRATO "+user.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return modelMapper.map(user, UserDto.class);
        }
        throw new EntityNotFoundException("non sono riuscito a fare la registrazione. PS: Questo messaggio non è stato internazionalizzato ed esploderà tra 3...2...1... papà");
    }

    @Override
    public TokenResponse doLogin(LoginUserDto data) {
        Optional<User> u = Optional.ofNullable(userDao.findUserByEmail(data.getEmail()).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"))));
        // NON FARE ALCUN sout DEGLI UTENTI! Data la pesantezza verrà dato un errore col toString() e col  java.lang.StackOverflowError
        // System.out.println(u);
        if (u.isPresent() && passwordEncoder.matches(data.getPassword(), u.get().getPassword())){
            TokenResponse tokenResponse =keycloakTokenClient.getToken(data.getEmail(), data.getPassword());
            UserDto userDto = modelMapper.map(u.get(),UserDto.class);
            tokenResponse.setUserDto(userDto);
            return tokenResponse;
        }

        throw new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"));
    }

}
