package it.unical.ea.VintedProject.data.service.interfaces;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.dto.OrderDto;
import org.springframework.data.domain.Page;

//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void add(Order order);

    Order save(Order order);

    OrderDto getOrderDtoById(Long id);

    void deleteOrderById(Long id);

    Page<OrderDto> getAllOrderDtoPaged(int page);

    Order getById(Long id);

    Page<OrderDto> getOrderDtoByUserIdPaged(Long UserId, int page);

    Page<OrderDto> getOrderDtoByIdAdminPaged(Long UserId, int page);

    Page<OrderDto> getOrderByIdAdminByEmail(String userEmail,int page);

    Page<OrderDto> getOrderDtoByPaymentPaged(String method, int page);

    void deleteOrderForAdmin(Long orderId);
}

