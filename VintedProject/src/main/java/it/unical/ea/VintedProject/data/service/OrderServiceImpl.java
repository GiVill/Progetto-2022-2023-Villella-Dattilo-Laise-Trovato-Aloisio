package it.unical.ea.VintedProject.data.service;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.entities.Favorite;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.dto.FavoriteDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ModelMapper modelMapper;

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


    public Order getOrderById(Long id) {
        return orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist any order with id: [%s]", id)));
    }

    @Override
    public void deleteOrderById(Long id) { orderDao.deleteById(id); }
}
