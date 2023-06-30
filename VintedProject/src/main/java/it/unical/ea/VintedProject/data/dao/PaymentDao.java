package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    //Return a SINGLE payment fetched by id
    @Override
    Optional<Payment> findById(Long aLong);

    //Return a LIST with ALL payment in the Database
    @Override
    List<Payment> findAll();

}
