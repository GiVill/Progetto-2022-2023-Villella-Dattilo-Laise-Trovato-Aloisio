package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.dto.OrderDto;


public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void add(Order order);

    Order save(Order order);

    Order getOrderById(Long id);

    void deleteOrderById(Long id);

}

