package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.data.dao.FavoriteDao;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final FavoriteDao favoriteDao;


    @Override
    public void save(User user) { userDao.save(user); }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a user with id: [%s]", id)));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = orderDao.findByUserOrder(userId);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException(String.format("Doesn't exist any order made from user with id: [%s]", userId));
        }
        return orders;
    }


    public List<Order> getFavoritesByUserId(Long userId) {
        List<Order> favorites = favoriteDao.findByFavoriteInsertion(userId);
        if (favorites.isEmpty()) {
            throw new EntityNotFoundException(String.format("Doesn't exist any favorite for user with id: [%s]", userId));
        }
        return favorites;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
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
