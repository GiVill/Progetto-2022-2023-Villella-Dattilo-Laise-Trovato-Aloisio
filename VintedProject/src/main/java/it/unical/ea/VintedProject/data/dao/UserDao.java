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

    //Return a SINGLE User fetched by Nickname
    Optional<User> findByNickName(String nickName);

    //Find a SINGLE User using email and password
    Optional<User> findByEmailAndPassword(String email, String password);
}
