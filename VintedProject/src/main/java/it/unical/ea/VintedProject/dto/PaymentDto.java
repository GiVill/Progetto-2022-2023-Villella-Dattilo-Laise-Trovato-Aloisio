package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumeration.PaymentMethod;
import it.unical.ea.VintedProject.dto.enumeration.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PaymentDto {

    @NotNull
    private Long id;

    private PaymentMethod paymentMethod;

    private Status status;

    @NotNull
    private Long userId;

    @NotNull
    private Long orderId;
}
