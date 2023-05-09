package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Payment;
import it.unical.ea.VintedProject.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    void save(Payment payment);

    void deleteOffer(Long id);

    List<PaymentDto> findAll();


}
