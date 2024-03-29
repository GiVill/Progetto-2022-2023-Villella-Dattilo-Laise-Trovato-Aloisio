package it.unical.ea.VintedProject.dto;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@ToString
public class OrderDto {

    @NotNull
    private Long id;

    private String date;

    private String paymentMethod;

    private List<Long> insertionIdList;

    private Float total;

    @NotNull
    private Long userId;

}
