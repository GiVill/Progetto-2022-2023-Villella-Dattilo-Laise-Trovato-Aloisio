package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public void add(User user) { userDao.save(user); }

    @Override
    public User save(User user) { return userDao.save(user); }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a user with id: [%s]", id)));
    }

    @Override
    public void deleteUserById(Long id) { userDao.deleteById(id); }
}
