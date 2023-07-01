package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


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

    //Find all the payment done by a single User and Returns them
    Page<Payment> findAllByUserId(Long userId, Pageable pageable);


}
