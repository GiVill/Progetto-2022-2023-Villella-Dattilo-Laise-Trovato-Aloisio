package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
