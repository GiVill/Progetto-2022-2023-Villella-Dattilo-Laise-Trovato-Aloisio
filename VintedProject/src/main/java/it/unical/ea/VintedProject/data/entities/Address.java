package it.unical.ea.VintedProject.data.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private Integer number;
    private String city;
    private Integer cap;
    private String state;
    private String region;
}
