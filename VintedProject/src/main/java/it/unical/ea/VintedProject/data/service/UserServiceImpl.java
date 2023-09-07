package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakTokenClient keycloakTokenClient;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final static int SIZE_FOR_PAGE = 10;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;

    @Override
    public void save(User user) { userDao.save(user); }

    @Override
    public UserDto saveDto(UserDto userDto) {
        User user  = modelMapper.map(userDto,User.class);
        userDao.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException((messageLang.getMessage("user.not.present",id))));
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("user.not.present",id)));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserDto> getAllUserDtoSortedByLastnameAscending() {
        return userDao.findAll( Sort.by("lastName").ascending()).stream().map(s -> modelMapper.map(s, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> getOptionalUserByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if(user.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("user.mail.not.present",email));
        }
        return user;
    }

    @Override
    public Boolean updateUserPassword(String newPassword) {
        try{
            User user = loggedUserMethod.getEntireLoggedUser();

            keycloakTokenClient.updateUserPassword(newPassword);
            user.setPassword(passwordEncoder.encode(newPassword));
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void deleteUserById(Long userId) {

        loggedUserMethod.checkLoggedUser(userId);
        userDao.deleteById(userId);
    }

}
