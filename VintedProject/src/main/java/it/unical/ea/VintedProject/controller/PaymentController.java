package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.PaymentDto;
import it.unical.ea.VintedProject.dto.UserDto;
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
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<PaymentDto> getById(@PathVariable("idPayment") Long id){ return ResponseEntity.ok(paymentService.findById(id)); }

    @GetMapping("/payments")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<List<PaymentDto>> all() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping("/payments")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<PaymentDto> addPayment (@RequestBody @Valid PaymentDto payment) { return ResponseEntity.ok(paymentService.save(payment)); }

    @DeleteMapping("payments/{idPayment}")
    //@PreAuthorize("hasAnyRole('user','admin')")
    public HttpStatus deleteOffer (@PathVariable("idPayment") Long id) {
        paymentService.deleteInsertion(id);
        return HttpStatus.OK;
    }

    @GetMapping("/payments/user/{userId}/{page}")
    public ResponseEntity<Page<PaymentDto>> getAllPaymentByUser(@PathVariable("userId") Long userId, @PathVariable("page") int page){
        return ResponseEntity.ok(paymentService.findAllByUser(userId, page));
    }
}
