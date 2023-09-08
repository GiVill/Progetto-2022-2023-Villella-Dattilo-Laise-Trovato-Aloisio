package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumeration.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BuyingOfferDto {

    private Long id;

    @NotNull
    private Float price;

    private Status status;

    @NotNull
    private Long insertionId;

    @NotNull
    private Long userId;

    private boolean paid;

}
