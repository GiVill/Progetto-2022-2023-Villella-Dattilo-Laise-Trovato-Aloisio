package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.UserDto;

import java.util.List;
import java.util.Optional;

//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

public interface UserService {

    void save(User user);

    UserDto saveDto(UserDto userDto);

    User getUserById(Long id);

    UserDto getUserDtoById(Long id);

    //List<Order> getOrdersByUser(Long userId);

    List<User> getAll();

    List<UserDto> getAllUserDtoSortedByLastnameAscending();

    Optional<User> getOptionalUserByEmail(String email);

    Boolean updateUserPassword(String newPassword);

    void deleteUserById(Long userId);
}
