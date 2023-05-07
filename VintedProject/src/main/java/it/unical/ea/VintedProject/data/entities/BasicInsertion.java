package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BASICINSERTION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BasicInsertion {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private Blob image;

    @Column(name = "CONDITION")
    private String condition;

    @OneToMany(mappedBy = "basicInsertionBuyingOffer", cascade = CascadeType.REMOVE)
    private List<BuyingOffer> buyingOffer;

    @OneToMany(mappedBy = "favoriteInsertion", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User userAuthor;


}
