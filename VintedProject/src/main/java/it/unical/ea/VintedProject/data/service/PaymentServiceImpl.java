package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.dao.PaymentDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.Payment;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.PaymentDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final ModelMapper modelMapper;
    private final MessageLang messageLang;
    private final UserDao userDao;;
    private final static int SIZE_FOR_PAGE = 5;

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
    public List<PaymentDto> findAll() {
        return paymentDao.findAll().stream().map(s -> modelMapper.map(s, PaymentDto.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentDto findById(Long id) {
        Payment payment = paymentDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("payment.not.present",id)));
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public Page<PaymentDto> findAllByUser(Long userId, int page) {

        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || userId !=u.get().getId()){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",userId));
        }

        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE);
        List<PaymentDto> collect = paymentDao.findAllByUserId(userId, pageRequest).stream().map(s -> modelMapper.map(s, PaymentDto.class)).collect(Collectors.toList());
        if(collect.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("user.without.payment",userId));
        }
        return new PageImpl<>(collect);
    }

    @Override
    public Page<PaymentDto> findAllForAdmin(Long userId, int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE);
        List<PaymentDto> collect = paymentDao.findAllByUserId(userId, pageRequest).stream().map(s -> modelMapper.map(s, PaymentDto.class)).collect(Collectors.toList());
        if(collect.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("user.without.payment",userId));
        }
        return new PageImpl<>(collect);
    }

    @Override
    public void deletePaymentAdmin(Long paymentId) {
        paymentDao.deleteById(paymentId);
    }

    @Override
    public void deletePayment(Long paymentId,Long userId) {

        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || userId !=u.get().getId()){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",userId));
        }
        Optional<Payment> payment = paymentDao.findById(paymentId);

        if(payment == null || !payment.get().getUser().getId().equals(userId))
        {
            throw new EntityNotFoundException(messageLang.getMessage("payment.not.present",userId));
        }
        paymentDao.deleteById(paymentId);
    }
}


