package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.security.keycloak.KeycloakTokenClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    //private final OrderDao orderDao;
    private final ModelMapper modelMapper;
    private final static int SIZE_FOR_PAGE = 10;
    private final MessageLang messageLang;


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
    public UserDto getById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("user.not.present",id)));
        return modelMapper.map(user, UserDto.class);
    }

    /*public List<Order> getOrdersByUser(Long userId) {
        List<Order> orders = orderDao.findByUser(userId);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException(messageLang.getMessage("user.order.not.present",userId));
        }
        return orders;
    }*/

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserDto> getAllStored() {
        return userDao.findAll( Sort.by("lastName").ascending()).stream().map(s -> modelMapper.map(s, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByNickName(String nickName) {
        Optional<User> user = userDao.findByNickName(nickName);
        if(user.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("user.nickname.not.present",nickName));
        }
        return user;
    }

    @Override
    public Boolean updateUserPassword(Long id, String newPassword) {
        //TODO L'update andrebbe fatta anche su Keycloak
        try{
            User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("user.not.present", id)));
            user.setPassword(newPassword);
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateUserNickname(Long id, String newNickname) {
        //TODO L'update andrebbe fatta anche su Keycloak
        try{
            User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("user.not.present", id)));
            user.setNickName(newNickname);
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void deleteUserById(Long id) { userDao.deleteById(id); }

}
