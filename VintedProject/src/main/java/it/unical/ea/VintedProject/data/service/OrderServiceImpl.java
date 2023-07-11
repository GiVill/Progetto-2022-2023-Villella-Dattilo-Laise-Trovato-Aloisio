package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
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
import org.springframework.data.domain.Pageable;
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
    public OrderDto getOrderById(Long orderId) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getOrders().contains(findById(orderId)) ){
            //TODO: ECCEZIONE CON MESSAGGIO
            System.out.println("NPOOOOOOOOOOOOOOO");
            throw  new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId));
        }
        Order order = orderDao.findById(orderId).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId)));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getOrders().contains(findById(orderId)) ){
            //TODO: ECCEZIONE CON MESSAGGIO
            System.out.println("NPOOOOOOOOOOOOOOO");
            throw  new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId));
        }
        orderDao.deleteById(orderId);
    }

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
    public Page<OrderDto> findByUserId(Long UserId,int page){
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(UserId) ){
            //TODO modificare l'eccezione
            throw new EntityNotFoundException(messageLang.getMessage("order.not.present",UserId));
        }
        Page<Order> orders = orderDao.findByUser(u,PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public OrderDto getOrderByIdAdmin(Long orderId) {
        Order order = orderDao.findById(orderId).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId)));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderForAdmin(Long orderId) {
        orderDao.deleteById(orderId);
    }

}
