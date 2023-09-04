package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import it.unical.ea.VintedProject.dto.enumeration.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Insert;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final BasicInsertionDao insertionDao;
    private final ModelMapper modelMapper;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;
    private final BuyingOfferService buyingOfferService;
    private final static int SIZE_FOR_PAGE = 5;
    //private LoggedUserDetail loggedUser = LoggedUserDetail.getInstance();

    @Override
    public OrderDto save(OrderDto orderDto) {

        //TODO: DEVO FARE UNA SAVE PRIMA PERCHé MI SERVE L'ID DELL'ORDINE PER CREARE LE RELAZIONI CON INSERTION DOPO
        Order order = orderDao.save(modelMapper.map(orderDto, Order.class));

        for (Long id:orderDto.getInsertionIdList()) {
            Optional<BasicInsertion> insertion = insertionDao.findById(id);
            if(insertion.isPresent()){
                insertion.get().setAvailable(false);
                insertion.get().setOrder(order);
                insertionDao.save(insertion.get());
            }
        }

        order.setDate(LocalDate.now());
        order.setStatus(Status.APPROVED);
        Order o = orderDao.save(order);
        return modelMapper.map(o, OrderDto.class);
    }

    @Override
    public OrderDto Offersave(OrderDto orderDto, Long offerId) {
        BuyingOfferDto offer = buyingOfferService.getOfferById(offerId);
        if(offer == null){
            throw new EntityNotFoundException(messageLang.getMessage("user.offer.not.present",offerId));
        }
        offer.setPaid(true);
        buyingOfferService.save(offer);

        //TODO: DEVO FARE UNA SAVE PRIMA PERCHé MI SERVE L'ID DELL'ORDINE PER CREARE LE RELAZIONI CON INSERTION DOPO
        Order order = orderDao.save(modelMapper.map(orderDto, Order.class));

        for (Long id:orderDto.getInsertionIdList()) {
            Optional<BasicInsertion> insertion = insertionDao.findById(id);
            if(insertion.isPresent()){
                insertion.get().setAvailable(false);
                insertion.get().setOrder(order);
                insertionDao.save(insertion.get());
            }
        }

        order.setDate(LocalDate.now());
        order.setStatus(Status.APPROVED);
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
        User u = loggedUserMethod.getEntireLoggedUser();
        Order order = orderDao.findById(orderId).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("order.not.present",orderId)));
        if(order.getUser().getId().equals(u.getId())){

            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            //TODO: LE LISTE DI ID NEI DTO NON FUNZIONANO NEL MODEL MAPPER

            List<Long> insertionIds = new ArrayList<>();
            for (BasicInsertion insertion : order.getInsertionList()) {
                insertionIds.add(insertion.getId());
            }
            orderDto.setInsertionIdList(insertionIds);


            return orderDto;
        }
        throw new BadRequestException(messageLang.getMessage("access.denied"));
    }

    @Override
    public void deleteOrderById(Long orderId) {
        //loggedUser.checkLoggedUser(...);
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
    public Page<OrderDto> getOrderDtoByUserIdPaged(Long userId, int page){
        loggedUserMethod.checkLoggedUser(userId);
        Page<Order> orders = orderDao.findByUserId(userId,PageRequest.of(page, SIZE_FOR_PAGE));
        List<Order> collect = orders.stream().toList();

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order: collect) {
            List<Long> insertionIds = new ArrayList<>();
            for (BasicInsertion insertion : order.getInsertionList()) {
                insertionIds.add(insertion.getId());
            }
            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setInsertionIdList(insertionIds);
            orderDtoList.add(orderDto);
        }
        return new PageImpl<>(orderDtoList);
    }

    @Override
    public  Page<OrderDto> getOrderDtoByIdAdminPaged(Long UserId, int page) {
        Optional<User> u = userDao.findById(UserId);
        if(u.get().getEmail() == null ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",UserId));
        }
        Page<Order> orders = orderDao.findByUserId(u.get().getId(), PageRequest.of(page, SIZE_FOR_PAGE));
        List<OrderDto> collect = orders.stream().map(s -> modelMapper.map(s, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<OrderDto> getOrderByIdAdminByEmail(String userEmail, int page) {
        Optional<User> u = userDao.findUserByEmail(userEmail);
        if(u.get().getEmail() == null ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",userEmail));
        }
        Page<Order> orders = orderDao.findByUserId(u.get().getId(), PageRequest.of(page, SIZE_FOR_PAGE));
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
