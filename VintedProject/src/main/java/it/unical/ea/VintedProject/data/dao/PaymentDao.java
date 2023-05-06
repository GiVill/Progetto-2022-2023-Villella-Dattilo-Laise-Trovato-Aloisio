package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {
}
