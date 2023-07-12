package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.PaymentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Payment") //Name displayed on swagger
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payments/{idPayment}")
    public ResponseEntity<PaymentDto> getById(@PathVariable("idPayment") Long id){ return ResponseEntity.ok(paymentService.findById(id)); }

    @GetMapping("/payments")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<PaymentDto>> all() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentDto> addPayment (@RequestBody @Valid PaymentDto payment) { return ResponseEntity.ok(paymentService.save(payment)); }

    @DeleteMapping("/payments/{idPayment}/{idUser}")
    public HttpStatus deletePayment(@PathVariable("idPayment") Long paymentId,@PathVariable("idUser") Long userId) {
        paymentService.deletePayment(paymentId,userId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/payment/admin/{paymentId}")
    //@PreAuthorize("hasRole('admin')")
    public HttpStatus deletePaymentAdmin(@PathVariable("paymentId") Long paymentId) {
        paymentService.deletePaymentAdmin(paymentId);
        return HttpStatus.OK;
    }

    @GetMapping("/payments/user/{userId}/{page}")
    public ResponseEntity<Page<PaymentDto>> getAllPaymentByUser(@PathVariable("userId") Long userId, @PathVariable("page") int page){
        return ResponseEntity.ok(paymentService.findAllByUser(userId, page));
    }

    @GetMapping("/payments/admin/{userId}/{page}")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<Page<PaymentDto>> getAllPaymentByAdmin(@PathVariable("userId") Long userId, @PathVariable("page") int page){
        return ResponseEntity.ok(paymentService.findAllForAdmin(userId, page));
    }
}
