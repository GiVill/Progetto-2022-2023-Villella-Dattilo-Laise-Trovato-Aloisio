package it.unical.ea.VintedProject.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Order") //Name displayed on swagger
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders/{id}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) { return ResponseEntity.ok(orderService.getOrderById(id)); }

    @GetMapping("/orders")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Page<OrderDto>> all(@RequestParam("page") int page){ return ResponseEntity.ok(orderService.getAllPaged(page)); }

    @PostMapping("/orders")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<OrderDto> addOrder(@RequestBody @Valid OrderDto orderDto) { return ResponseEntity.ok(orderService.save(orderDto)); }

    @DeleteMapping("/orders/{id}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("orders/user/{userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }

}