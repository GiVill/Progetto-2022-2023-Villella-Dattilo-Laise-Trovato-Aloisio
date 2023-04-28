package it.unical.ea.VintedProject.dao;

import it.unical.ea.VintedProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    //List<User> findAllByUserFirstName(String name);
}
