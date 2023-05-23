package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.PaymentDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.Payment;
import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.PaymentDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final ModelMapper modelMapper;

    @Override
    public PaymentDto save(PaymentDto payment) {
        Payment payment1 = modelMapper.map(payment,Payment.class);
        Payment o = paymentDao.save(payment1);
        return modelMapper.map(o,PaymentDto.class);
    }
    @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }

    @Override
    public void deleteInsertion(Long id) {
        paymentDao.deleteById(id);
    }

    @Override
    public List<PaymentDto> findAll() {
        return paymentDao.findAll().stream().map(s -> modelMapper.map(s, PaymentDto.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentDto findById(Long id) {
        Payment payment = paymentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("ERRORE!!!!!!!!!!"));
        return modelMapper.map(payment, PaymentDto.class);
    }
}


