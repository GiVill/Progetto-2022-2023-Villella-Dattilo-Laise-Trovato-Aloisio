package it.unical.ea.VintedProject.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class BasicInsertionDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Float price;

    private String description;

    private String condition;

    @NotNull
    private LocalDate creationDate;

    private LocalDate endDate;

    private String imagePath;

    @NotNull
    private Long userId;
}
