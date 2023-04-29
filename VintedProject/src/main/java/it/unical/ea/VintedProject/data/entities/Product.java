package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    /*
    Prodotto:
        -ID
        -Nome
        -Prezzo
        -Marca
        -Descrizione
        -Img.
        -Categoria
        -Condizione
        -Disponibiltà
        -(ID-Utente)
     */

    public enum Category{
        ELECTRONIC,COLLECTION,FASHION,BOAT,SPORT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private Blob image;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "CONDITION")
    private String condition;

    @Column(name = "AVAILABILITY")
    private boolean availability;

}
