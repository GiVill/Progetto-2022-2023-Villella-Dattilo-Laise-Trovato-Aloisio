package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.core.entitiesAuditTrailListener.OrderListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, OrderListener.class})
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @Column(name = "NUMBER")
    private Integer number;

    @OneToOne
    @JoinColumn(name = "INSERTION_ID", referencedColumnName = "ID")
    private BasicInsertion insertion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
