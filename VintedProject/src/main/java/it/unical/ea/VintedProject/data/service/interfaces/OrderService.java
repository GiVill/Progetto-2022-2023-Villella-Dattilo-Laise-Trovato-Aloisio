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

    Page<OrderDto> findByUserId(Long UserId,int page);

    Page<OrderDto> getOrderByIdAdmin(Long UserId,int page);

    Page<OrderDto> getOrderByIdAdminByEmail(String userEmail,int page);


    void deleteOrderForAdmin(Long orderId);
}

