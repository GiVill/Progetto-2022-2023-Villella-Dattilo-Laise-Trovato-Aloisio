package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;



public interface OrderService {
    void add(Order order);

    Order save(Order order);

    Order getOrderById(Long id);

    void deleteOrderById(Long id);
}

