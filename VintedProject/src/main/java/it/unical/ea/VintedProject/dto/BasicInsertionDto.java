package it.unical.ea.VintedProject.dto;


import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
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

    private LocalDate creationDate;

    private Boolean isPrivate;

    private LocalDate endDate;

    private String imageName;

    private Brand brand;

    private Category category;

    @NotNull
    private Long userId;
}
