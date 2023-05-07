package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import java.util.List;

public interface UserService {

    void add(User user);

    User save(User user);

    User getUserById(Long id);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getFavoritesByUserId(Long userId);

    void deleteUserById(Long id);

}
