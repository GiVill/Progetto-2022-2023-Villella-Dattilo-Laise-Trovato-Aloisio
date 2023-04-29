package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Order {

    /*
    Ordine:
        -ID
        -Data
        -Metodo pagamento
        -Numero
     */

    public enum PaymentMethod{
        CARD,PAYPAL,MARK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column(name = "NUMBER")
    private Integer number;


}
