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

    //inserzione sulla quale e stata fatto il pagamento
    @OneToOne
    @JoinColumn(name = "basicinsertion_id", referencedColumnName = "ID")
    private BasicInsertion insertion;

    //utente che ha effettuato il pagamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
