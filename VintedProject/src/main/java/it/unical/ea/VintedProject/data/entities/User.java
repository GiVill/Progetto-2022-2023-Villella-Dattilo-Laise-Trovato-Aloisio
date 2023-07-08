package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.UserListener;
import it.unical.ea.VintedProject.dto.enumeration.Gender;
import it.unical.ea.VintedProject.dto.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, UserListener.class})
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NICKNAME", unique = true)
    private String nickName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

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

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BuyingOffer> buyingOffers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BasicInsertion> insertions;

    @ManyToMany
    @JoinTable(
            name = "USER_FAVORITE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BASICINSERTION_ID"))
    Set<BasicInsertion> favorites;

}
