package it.unical.ea.VintedProject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PaymentDto {

    private Long id;

    private Long userPayment;
}
