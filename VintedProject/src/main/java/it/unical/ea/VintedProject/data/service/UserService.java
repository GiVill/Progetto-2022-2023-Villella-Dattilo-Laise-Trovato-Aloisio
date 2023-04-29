package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.entities.User;

public interface UserService {

    void add(User user);

    User save(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

}
