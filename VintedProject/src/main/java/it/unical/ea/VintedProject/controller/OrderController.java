package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


        private final OrderService orderService;
        private final UserService userService;

        public OrderController(OrderService orderService, UserService userService) {
            this.orderService = orderService;
            this.userService = userService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(convertToDto(order));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
            orderService.deleteOrderById(id);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/")
        public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
            Order order = convertToEntity(orderDto);
            orderService.add(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(order));
        }

        private Order convertToEntity(OrderDto orderDto) {
            Order order = new Order();
            order.setDate(orderDto.getDate());
            order.setPaymentMethod(orderDto.getPaymentMethod());
            order.setNumber(orderDto.getNumber());
            User user = userService.getUserById(orderDto.getUserId());
            order.setUserOrder(user);
            return order;
        }

        private OrderDto convertToDto(Order order) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setDate(order.getDate());
            orderDto.setPaymentMethod(order.getPaymentMethod());
            orderDto.setNumber(order.getNumber());
            orderDto.setUserId(order.getUserOrder().getId());
            return orderDto;
        }
    }


