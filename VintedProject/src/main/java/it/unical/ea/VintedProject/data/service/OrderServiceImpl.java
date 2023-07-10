package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
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
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final MessageLang messageLang;
    private final static int SIZE_FOR_PAGE = 5;

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order o = orderDao.save(order);
        return modelMapper.map(o, OrderDto.class);
    }

    @Override
    public void add(Order order) { orderDao.save(order); }

    @Override
    public Order save(Order order) { return orderDao.save(order); }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",id)));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderById(Long id) { orderDao.deleteById(id); }

    @Override
    public Page<OrderDto> getAllPaged(int page) {
        Page<Order> orders = orderDao.findAll(PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",id)));
    }

    @Override
    public List<OrderDto> findByUserId(Long UserId){
        Optional<User> u = userDao.findById(UserId);
        if (u != null){
            List<Order> orders = orderDao.findByUser(u);
            return orders.stream().map(s -> modelMapper.map(s,OrderDto.class)).collect(Collectors.toList());
        }
        return null;
    }
}
