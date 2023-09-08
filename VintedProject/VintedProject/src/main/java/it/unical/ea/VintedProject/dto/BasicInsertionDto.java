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

    private Long id;
    @NotNull(message = "title.not.empty")
    private String title;
    @NotNull(message = "price.not.empty")
    private Float price;
    @NotNull(message = "description.not.empty")
    private String description;
    private LocalDate creationDate;
    private Boolean isPrivate;
    private String imageName;
    private Brand brand;
    private Category category;
    private Boolean available;
    @NotNull(message = "userId.not.empty")
    private Long userId;
}
