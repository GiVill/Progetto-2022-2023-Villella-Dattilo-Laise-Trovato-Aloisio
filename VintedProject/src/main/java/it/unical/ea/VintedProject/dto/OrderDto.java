package it.unical.ea.VintedProject.dto;
import lombok.Data;
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
