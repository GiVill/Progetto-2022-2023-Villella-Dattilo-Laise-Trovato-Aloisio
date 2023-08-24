package it.unical.ea.VintedProject.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Order") //Name displayed on swagger
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/orders/admin/id/{userId}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderByIdAdmin(@PathVariable("userId") Long UserId,@PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderByIdAdmin(UserId, page));
    }

    @GetMapping("/orders/admin/email/{userEmail}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderByIdAdminByEmail(@PathVariable("userEmail") String email,@PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderByIdAdminByEmail(email, page));
    }

    @GetMapping("/orders")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Page<OrderDto>> all(@RequestParam("page") int page){
        return ResponseEntity.ok(orderService.getAllPaged(page));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        System.out.println("AFFA");
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders/admin/{orderId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteOrderForAdmin(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrderForAdmin(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/user/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("page") int page){
        Optional<User> u = userService.findByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null){
            throw new EntityNotFoundException("CACCA");
        }
        return ResponseEntity.ok(orderService.findByUserId(u.get().getId(), page));
    }

    /* TODO: ADMIN
    @GetMapping("/orders/user/{userId}/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("userId") Long userId,@PathVariable("page") int page){
        return ResponseEntity.ok(orderService.findByUserId(userId,page));
    }

     */

}
