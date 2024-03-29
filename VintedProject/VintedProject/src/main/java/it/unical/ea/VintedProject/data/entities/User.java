package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.UserListener;
import it.unical.ea.VintedProject.dto.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, UserListener.class})
@Table(name = "USERS")
public class User {

    @Id
    //Abbiamo lasciato GenerationType.IDENTITY per non avere problemi nel dbgenerator
    //Andrebbe cambiato con GenerationType.UUID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "ADDRESS")
    @Embedded
    private Address address;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL", unique = true)
    @Email
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    @Column(name = "PRIVATE")
    private Boolean isPrivate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BuyingOffer> buyingOffers;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BasicInsertion> insertions;

}
