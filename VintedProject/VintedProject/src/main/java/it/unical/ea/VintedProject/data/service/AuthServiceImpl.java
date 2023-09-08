package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.config.newsletter.EmailSender;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.AuthService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.NewUserDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.dto.enumeration.Role;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import it.unical.ea.VintedProject.security.keycloak.TokenDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmailSender emailSender;
    private final ModelMapper modelMapper;
    private final UserDao userDao;
    private final KeycloakTokenClient keycloakTokenClient;
    private final PasswordEncoder passwordEncoder;
    private final MessageLang messageLang;

    @Override
    public UserDto signUp(NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        if(user != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            emailSender.sendSimpleEmail(user.getEmail(),"Ocarina Coders", "Benvenuto in Ocarina Shop!");
            return modelMapper.map(user, UserDto.class);
        }
        throw new EntityNotFoundException(messageLang.getMessage("user.not.present"));
    }

    @Override
    public TokenDto doLogin(LoginUserDto data) {
        Optional<User> u = Optional.ofNullable(userDao.findUserByEmail(data.getEmail()).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"))));

        if (u.isPresent() && passwordEncoder.matches(data.getPassword(), u.get().getPassword())){
            TokenDto tokenResponse =keycloakTokenClient.getToken(data.getEmail(), data.getPassword());
            UserDto userDto = modelMapper.map(u.get(),UserDto.class);
            tokenResponse.setUserDto(userDto);
            return tokenResponse;
        }

        throw new EntityNotFoundException(messageLang.getMessage("credentials.not.valid"));
    }

}
