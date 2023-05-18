package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.dto.enumerated.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BuyingOfferDto {

    private Long id;

    private Float price;

    private Status status;

    private Long insertionId;

    private Long userId;

}
