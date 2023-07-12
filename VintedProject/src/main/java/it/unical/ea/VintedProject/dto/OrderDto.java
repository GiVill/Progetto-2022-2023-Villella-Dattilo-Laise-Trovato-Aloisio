package it.unical.ea.VintedProject.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@ToString
public class OrderDto {

    @NotNull
    private Long id;

    private LocalDate date;

    @NotNull
    private Integer number; //Todo da togliere?

    @NotNull
    private Long paymentId;

    @NotNull
    private Long insertionId;

    @NotNull
    private Long userId;

}
