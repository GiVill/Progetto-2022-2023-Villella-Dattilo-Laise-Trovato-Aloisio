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
    //private LoggedUserDetail loggedUser = LoggedUserDetail.getInstance();
    private final LoggedUserMethod loggedUserMethod;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderDtoById(@PathVariable("orderId") Long orderId) {
        // Check the Token, if not ok: THROW Exception.
        // find Order using the ID, if not existent: THROW Exception.
        return ResponseEntity.ok(orderService.getOrderDtoById(orderId));
    }

    @GetMapping("/orders/admin/id/{userId}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderDtoByIdPagedAdmin(@PathVariable("userId") Long UserId, @PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderDtoByIdAdminPaged(UserId, page));
    }

    @GetMapping("/orders/admin/email/{userEmail}/{page}")
    //@PreAuthorize("hasRole('<admin')")
    public ResponseEntity<Page<OrderDto>> getOrderDtoByIdByEmailPagedAdmin(@PathVariable("userEmail") String email, @PathVariable("page") int page) {
        return ResponseEntity.ok(orderService.getOrderByIdAdminByEmail(email, page));
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderDto>> getAll(@RequestParam("page") int page){
        //Return all the Order (as OrderDto), paged
        //TODO no Throw handled!
        return ResponseEntity.ok(orderService.getAllOrderDtoPaged(page));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        //Save an orderDto and return
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("orderId") Long orderId) {
        // Check the Token, if ok: delete an Order using the ID.
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/orders/admin/{orderId}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteOrderForAdmin(@PathVariable("orderId") Long orderId) {
        // Delete an Order using the ID.
        // No Throw, No Token
        orderService.deleteOrderForAdmin(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/user/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("page") int page){
        // Check the token, if ok: get OrderDto (as Paged) using the id of the user

        /*Optional<User> u = userService.findByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null){
            throw new EntityNotFoundException("CACCA");
        }*/

        //Long userId = loggedUser.getLoggedUserId();
        Long userId = loggedUserMethod.getLoggedUserId();
        return ResponseEntity.ok(orderService.getOrderDtoByUserIdPaged(userId, page));
    }

    /* TODO: ADMIN
    @GetMapping("/orders/user/{userId}/{page}")
    public ResponseEntity<Page<OrderDto>> getUserOrders(@PathVariable("userId") Long userId,@PathVariable("page") int page){
        return ResponseEntity.ok(orderService.findByUserId(userId,page));
    }

     */

}
