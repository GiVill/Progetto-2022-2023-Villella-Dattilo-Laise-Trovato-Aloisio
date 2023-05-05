package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.dto.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OFFER")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column
    private Float price;


    //utente che ha fatto l'offerta
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User offer_from_user;

    //inserzione sulla quale è stata fatta l'offerta
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "insertion_id", referencedColumnName = "ID")
    private Insertion insertion_offer;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

}
