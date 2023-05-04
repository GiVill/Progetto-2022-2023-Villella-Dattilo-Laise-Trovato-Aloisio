package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.dto.Gender;
import it.unical.ea.VintedProject.dto.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;

import javax.management.relation.Role;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    /*
    Utente:
        -ID
        -email
        -Nome
        -Cognome
        -Ruolo
        -Password
        -Indirizzo
        -Numero
        -Genere
        -Img.
        -(ID-Prodotti)
     */
    //dio cane

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
    private LocalDate birthDate;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //todo: chiedere al prof come gestire le foto
    private Blob photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Insertion> insertions;

}
