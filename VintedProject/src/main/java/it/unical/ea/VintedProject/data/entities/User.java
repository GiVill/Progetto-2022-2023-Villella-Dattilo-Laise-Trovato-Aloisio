package it.unical.ea.VintedProject.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    //todo: chiedere al prof dove mettere gli enum
    public enum Role{
        ADMIN,USER;
    }

    public enum Gender{
        MALE,FEMALE,OTHER;
    }

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
    private int phoneNumber;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //todo: chiedere al prof come gestire le foto
    private Blob photo;
}
