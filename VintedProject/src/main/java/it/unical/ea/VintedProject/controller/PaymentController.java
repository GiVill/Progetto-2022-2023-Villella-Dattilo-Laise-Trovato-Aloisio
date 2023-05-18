package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.PaymentDto;
import it.unical.ea.VintedProject.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vintedProject-api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<PaymentDto> addPayment (@RequestBody @Valid PaymentDto payment) {
        return ResponseEntity.ok(paymentService.save(payment));
    }

    @DeleteMapping("payments/{idPayment}")
    public HttpStatus deleteOffer (@PathVariable("idPayment") Long id) {
        paymentService.deleteInsertion(id);
        return HttpStatus.OK;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> all() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable("idPayment") Long id){
        PaymentDto paymentDto = paymentService.findById(id);
        return ResponseEntity.ok(paymentDto);
    }
}
