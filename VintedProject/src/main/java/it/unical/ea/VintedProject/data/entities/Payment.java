package it.unical.ea.VintedProject.data.entities;

import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.PaymentListener;
import it.unical.ea.VintedProject.dto.enumeration.PaymentMethod;
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
@EntityListeners(value = {AuditingEntityListener.class, PaymentListener.class})
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    //inserzione sulla quale e stata fatto il pagamento
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "ID")
    private Order order;

    //utente che ha effettuato il pagamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
