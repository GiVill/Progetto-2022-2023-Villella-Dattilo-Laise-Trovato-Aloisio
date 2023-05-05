package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,Long> {
}
