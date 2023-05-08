package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {


        private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    }


