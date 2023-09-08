package it.unical.ea.VintedProject.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user1", "user2","insertionId"})
})
public class Chat {
    @Id
    //Abbiamo lasciato GenerationType.IDENTITY per non avere problemi nel dbgenerator
    //Andrebbe cambiato con GenerationType.UUID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long id;

    @Column(name = "user1")
    private Long user1;

    @Column(name = "user2")
    private Long user2;

    @Column(name = "user1NameSurname")
    private String user1NameLastname;

    @Column(name = "user2NameSurname")
    private String user2NameLastname;

    @Column(name = "insertionId")
    private Long insertionId;

    @Column(name = "InsertionTitle")
    private String InsertionTitle;



}
