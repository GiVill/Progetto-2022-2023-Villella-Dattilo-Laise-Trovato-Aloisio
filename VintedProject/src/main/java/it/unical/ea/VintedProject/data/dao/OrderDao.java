package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

    //Return an Order using the id of a User
    List<Order> findByUser(Optional<User> user);

    List<Order> findByUser(User user);

}
