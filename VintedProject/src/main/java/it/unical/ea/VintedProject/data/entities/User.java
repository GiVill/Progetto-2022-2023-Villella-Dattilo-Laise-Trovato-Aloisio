package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.dto.enumerated.Gender;
import it.unical.ea.VintedProject.dto.enumerated.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "ADDRESS")
    @Embedded
    private Address address;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //todo: chiedere al prof come gestire le foto
    private Blob photo;

    @OneToMany(mappedBy = "userAuthor", cascade = CascadeType.REMOVE)
    private List<BasicInsertion> basicInsertions;

    @OneToMany(mappedBy = "userFavorite", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "userBuyingOffer", cascade = CascadeType.REMOVE)
    private List<BuyingOffer> buyingOffers;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    @OneToMany(mappedBy = "userPayment", cascade = CascadeType.REMOVE)
    private List<Payment> payments;

}
