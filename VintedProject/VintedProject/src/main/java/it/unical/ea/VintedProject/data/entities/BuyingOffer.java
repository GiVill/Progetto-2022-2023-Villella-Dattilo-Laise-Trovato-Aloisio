package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.BuyingOfferListener;
import it.unical.ea.VintedProject.dto.enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, BuyingOfferListener.class})
@Table(name = "BUYINGOFFER")
public class BuyingOffer {

    @Id
    //Abbiamo lasciato GenerationType.IDENTITY per non avere problemi nel dbgenerator
    //Andrebbe cambiato con GenerationType.UUID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column
    private Float price;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSERTION_ID")
    private BasicInsertion insertion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column
    private Boolean paid;

}
