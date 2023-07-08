package it.unical.ea.VintedProject.dto;


import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
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

    private Boolean isPrivate;

    private LocalDate endDate;

    private String imageName;

    @NotNull
    private Long userId;
}
