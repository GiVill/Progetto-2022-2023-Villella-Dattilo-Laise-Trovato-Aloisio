package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.InsertionListener;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, InsertionListener.class})
@Table(name = "BASICINSERTION")
@Inheritance(strategy = InheritanceType.JOINED)
public class BasicInsertion {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AVAILABLE")
    private Boolean available;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @Column(name = "PRIVATE")
    private Boolean isPrivate;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "BRAND")
    private Brand brand;

    @OneToMany(mappedBy = "insertion", fetch = FetchType.LAZY)
    private List<BuyingOffer> buyingOffers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERS_ID")
    private Order order;
}
