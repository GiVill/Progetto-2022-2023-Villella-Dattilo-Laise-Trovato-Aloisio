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
        @UniqueConstraint(columnNames = {"user1", "user2","BASICINSERTION_ID"})
})
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long id;

    @Column(name = "user1")
    private Long user1;

    @Column(name = "user2")
    private Long user2;
    
    @OneToOne
    @JoinColumn(name = "BASICINSERTION_ID")
    private BasicInsertion basicInsertion;


}
