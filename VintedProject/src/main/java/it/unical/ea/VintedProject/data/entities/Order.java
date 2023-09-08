package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.OrderListener;
import it.unical.ea.VintedProject.dto.enumeration.PaymentMethod;
import it.unical.ea.VintedProject.dto.enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, OrderListener.class})
@Table(name = "ORDERS")
public class Order {

    @Id
    //Abbiamo lasciato GenerationType.IDENTITY per non avere problemi nel dbgenerator
    //Andrebbe cambiato con GenerationType.UUID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "TOTAL")
    private Float total;

    @Column(name = "PAYMENT_METHOD")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<BasicInsertion> insertionList ;

}
