package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Payment;
import it.unical.ea.VintedProject.dto.PaymentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {

    PaymentDto save(PaymentDto payment);

    void deleteInsertion(Long id);

    List<PaymentDto> findAll();

    PaymentDto findById(Long id);

    void save(Payment payment);

    Page<PaymentDto> findAllByUser(Long userId, int page);

}
