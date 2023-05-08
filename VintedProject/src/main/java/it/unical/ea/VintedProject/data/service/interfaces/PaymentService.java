package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Payment;

import java.util.List;

public interface PaymentService {

    void save(Payment payment);

    void delete(Long id);

    List<Payment> findAll();

}
