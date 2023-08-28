package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
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
    //private LoggedUserDetail loggedUser = LoggedUserDetail.getInstance();

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
    public OrderDto getOrderDtoById(Long orderId) {
        //loggedUser.checkLoggedUser(...);
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getOrders().contains(getById(orderId)) ){
            throw  new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId));
        }
        Order order = orderDao.findById(orderId).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId)));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        //loggedUser.checkLoggedUser(...);
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getOrders().contains(getById(orderId)) ){
            throw  new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId));
        }
        orderDao.deleteById(orderId);
    }

    @Override
    public Page<OrderDto> getAllOrderDtoPaged(int page) {
        Page<Order> orders = orderDao.findAll(PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",id)));
    }

    @Override
    public Page<OrderDto> getOrderDtoByUserIdPaged(Long UserId, int page){
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(UserId) ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",UserId));
        }
        Page<Order> orders = orderDao.findByUser(u,PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public  Page<OrderDto> getOrderDtoByIdAdminPaged(Long UserId, int page) {
        Optional<User> u = userDao.findById(UserId);
        if(u.get().getEmail() == null ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",UserId));
        }
        Page<Order> orders = orderDao.findByUser(u,PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<OrderDto> getOrderByIdAdminByEmail(String userEmail, int page) {
        Optional<User> u = userDao.findUserByEmail(userEmail);
        if(u.get().getEmail() == null ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",userEmail));
        }
        Page<Order> orders = orderDao.findByUser(u,PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }



    @Override
    public Page<OrderDto> getOrderDtoByPaymentPaged(String method, int page) {
        //TODO AGGIUNGERE I CONTROLLI
        Page<Order> orders = orderDao.findAllByPaymentMethod(method,PageRequest.of(page,SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public void deleteOrderForAdmin(Long orderId) {
        orderDao.deleteById(orderId);
    }

}
