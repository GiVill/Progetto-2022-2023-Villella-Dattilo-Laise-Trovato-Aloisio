package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.UserDto;
import it.unical.ea.VintedProject.exception.UserException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final ModelMapper modelMapper;

    private final MessageLang messageLang;

    @Override
    public void save(User user) { userDao.save(user); }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a user with id: [%s]", id)));
    }

    @Override
    public UserDto getById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("user.not",id)));
        return modelMapper.map(user, UserDto.class);
    }

    public List<Order> getOrdersByUser(Long userId) {
        List<Order> orders = orderDao.findByUser(userId);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException(String.format("Doesn't exist any order made from user with id: [%s]", userId));
        }
        return orders;
    }

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
            throw new EntityNotFoundException(String.format("Doesn't exist any user with nickname: [%s]", nickName));
        }
        return user;
    }
    @Override
    public void deleteUserById(Long id) { userDao.deleteById(id); }
}
