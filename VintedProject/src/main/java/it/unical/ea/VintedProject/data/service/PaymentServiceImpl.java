package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.PaymentDao;
import it.unical.ea.VintedProject.data.entities.Payment;
import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.PaymentDto;
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
    public void save(Payment payment) {
        paymentDao.save(payment);
    }

    @Override
    public void deleteOffer(Long id) {
        paymentDao.deleteById(id);

    }

    @Override
    public List<PaymentDto> findAll() {
        return paymentDao.findAll().stream().map(s -> modelMapper.map(s, PaymentDto.class)).collect(Collectors.toList());
    }
}


