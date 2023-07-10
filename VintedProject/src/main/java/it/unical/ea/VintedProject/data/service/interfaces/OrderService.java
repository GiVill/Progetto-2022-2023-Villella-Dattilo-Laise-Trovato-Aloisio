package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.dto.OrderDto;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void add(Order order);

    Order save(Order order);

    OrderDto getOrderById(Long id);

    void deleteOrderById(Long id);

    Page<OrderDto> getAllPaged(int page);

    Order findById(Long id);

    List<OrderDto> findByUserId(Long UserId);

}

