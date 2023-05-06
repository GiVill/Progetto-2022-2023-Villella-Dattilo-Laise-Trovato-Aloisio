package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.dto.enumerated.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    /*
    //inserzione sulla quale e stata fatto il pagamento
    @OneToOne(mappedBy = "insertion_paymentID")
    private BasicInsertion insertionPayment;
     */

    //utente che ha effettuato il pagamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User userPayment;
}
