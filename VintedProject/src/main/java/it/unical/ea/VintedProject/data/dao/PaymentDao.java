package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    @Override
    Optional<Payment> findById(Long aLong);

    @Override
    List<Payment> findAll();



}
