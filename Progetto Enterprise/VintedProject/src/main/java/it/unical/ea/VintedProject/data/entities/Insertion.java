package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSERTION")
public class Insertion {

    /*
    Inserzione ?:
        -ID
        -ProdottoID
        -UtenteID(crea)
        -Data(inzio)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /*
    @Column(name = "")
    private Long prodottoID;

    @Column(name = "")
    private ;

    @Column(name = "")
    private ;
    */

    //id dell'utente che ha pubblicato l'inserzione
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User insertion_user;

    //id delle offerte su questa inserzione
    @OneToMany(mappedBy = "insertion_offer", fetch = FetchType.LAZY)
    private List<Offer> offers_on_this;

    //pagamento relativo a questa inserzione
    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "ID")
    private Payment insertion_paymentID;
}
