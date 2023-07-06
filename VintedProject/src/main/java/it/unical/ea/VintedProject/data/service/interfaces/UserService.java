package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    UserDto saveDto(UserDto userDto);

    void deleteUserById(Long id);

    User getUserById(Long id);

    UserDto getById(Long id);

    List<Order> getOrdersByUser(Long userId);

    List<User> findAll();

    List<UserDto> getAllStored();

    Optional<User> findByNickName(String nickName);

    Boolean updateUserPassword(Long id, String newPassword);

    Boolean updateUserNickname(Long id, String newNickname);


}
