package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    void deleteUserById(Long id);

    User getUserById(Long id);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getFavoritesByUserId(Long userId);

    List<User> findAll();

    Optional<User> findByNickName(String nickName);

}
