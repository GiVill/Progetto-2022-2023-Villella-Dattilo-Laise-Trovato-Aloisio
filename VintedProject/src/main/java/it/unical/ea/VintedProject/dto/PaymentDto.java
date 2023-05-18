package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumerated.PaymentMethod;
import it.unical.ea.VintedProject.dto.enumerated.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PaymentDto {

    private Long id;

    private PaymentMethod paymentMethod;

    private Status status;

    private Long userId;

    private Long orderId;
}
