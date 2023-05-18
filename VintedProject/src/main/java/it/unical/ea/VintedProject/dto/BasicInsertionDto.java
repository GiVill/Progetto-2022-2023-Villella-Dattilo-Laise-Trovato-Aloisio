package it.unical.ea.VintedProject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class BasicInsertionDto {

    private Long id;

    private String title;

    private Integer price;

    private String description;

    private String condition;

    private LocalDate creationDate;

    private LocalDate endDate;

    //TODO: Immagini ?????????

    private Long userId;
}
