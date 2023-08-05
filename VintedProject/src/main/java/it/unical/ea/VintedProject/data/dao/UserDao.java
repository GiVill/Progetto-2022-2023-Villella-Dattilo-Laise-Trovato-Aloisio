package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.LoginUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    //Return the LIST of ALL User fetched by FirstName
    List<User> findAllByFirstName(String firstName);

    //Return a SINGLE User fetched by id
    @Override
    Optional<User> findById(Long aLong);

    //Find a SINGLE User using email
    Optional<User> findUserByEmail(String email);

    User findById(User idUser2);
}
