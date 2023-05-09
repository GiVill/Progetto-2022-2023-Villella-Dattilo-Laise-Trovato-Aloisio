package it.unical.ea.VintedProject.controller;

import it.unical.ea.VintedProject.data.service.interfaces.PaymentService;
import it.unical.ea.VintedProject.dto.PaymentDto;
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
        paymentService.deleteOffer(id);
        return HttpStatus.OK;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> all() {
        return ResponseEntity.ok(paymentService.findAll());
    }
}
