package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    List<User> findAllByFirstName(String firstName);
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByNickName(String nickName);

}
