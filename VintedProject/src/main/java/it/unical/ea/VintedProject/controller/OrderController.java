package it.unical.ea.VintedProject.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.service.interfaces.OrderService;
import it.unical.ea.VintedProject.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Order") //Name displayed on swagger
public class OrderController {

    private final OrderService orderService;
    private final LoggedUserMethod loggedUserMethod;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderDtoById(@PathVariable("orderId") Long orderId) {
        // Check the Token, if not ok: THROW Exception.
        // find Order using the ID, if not existent: THROW Exception.
        return ResponseEntity.ok(orderService.getOrderDtoById(orderId));
    }

    @GetMapping("/admin/orders/{userId}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderDtoByIdPagedAdmin(@PathVariable("userId") Long UserId, @PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderDtoByIdAdminPaged(UserId, page));
    }

    @GetMapping("/admin/orders/email/{userEmail}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderDtoByIdByEmailPagedAdmin(@PathVariable("userEmail") String email, @PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderByIdAdminByEmail(email, page));
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<Page<OrderDto>> getAll(@RequestParam("page") int page){
        //Return all the Order (as OrderDto), paged
        return ResponseEntity.ok(orderService.getAllOrderDtoPaged(page));
    }

    @PostMapping("/admin/orders")
    public ResponseEntity<OrderDto> adminAddOrder(@RequestBody OrderDto orderDto) {
        //Save an orderDto and return
        return ResponseEntity.ok(orderService.save(orderDto));
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> userAddOrder(@RequestBody OrderDto orderDto) {
        //Save an orderDto and return
        loggedUserMethod.checkLoggedUser(orderDto.getUserId());
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @PostMapping("/offer/orders")
    public ResponseEntity<OrderDto> userAddOfferOrder(@RequestBody OrderDto orderDto, Long offerId) {
        //Save an orderDto and return
        loggedUserMethod.checkLoggedUser(orderDto.getUserId());
        return ResponseEntity.ok(orderService.Offersave(orderDto, offerId));
    }

    @DeleteMapping("/admin/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("orderId") Long orderId) {
        // Check the Token, if ok: delete an Order using the ID.
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteOrder(@RequestBody OrderDto order) {
        // Delete an Order using the ID.
        // No Throw, No Token
        loggedUserMethod.checkLoggedUser(order.getUserId());
        orderService.deleteOrderById(order.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/user/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("page") int page){
        // Check the token, if ok: get OrderDto (as Paged) using the id of the user
        Long userId = loggedUserMethod.getLoggedUserId();
        return ResponseEntity.ok(orderService.getOrderDtoByUserIdPaged(userId, page));
    }

    @GetMapping("admin/orders/user/{userId}/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("userId") Long userId,@PathVariable("page") int page){
        return ResponseEntity.ok(orderService.getOrderDtoByIdAdminPaged(userId,page));
    }



}
