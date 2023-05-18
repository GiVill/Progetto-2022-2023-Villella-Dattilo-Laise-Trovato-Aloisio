package it.unical.ea.VintedProject.dto;
import it.unical.ea.VintedProject.dto.enumerated.PaymentMethod;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@ToString
public class OrderDto {

    private Long id;

    private LocalDate date;

    private Long paymentId;

    private Integer number;

    private Long insertionId;

    private Long userId;

}
