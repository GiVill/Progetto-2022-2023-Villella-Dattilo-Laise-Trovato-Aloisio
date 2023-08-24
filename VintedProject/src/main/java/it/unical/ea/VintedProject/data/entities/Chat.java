package it.unical.ea.VintedProject.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sender", "reciver","BASICINSERTION_ID"})
})
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long id;

    @Column(name = "sender")
    private Long sender;

    @Column(name = "reciver")
    private Long reciver;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "BASICINSERTION_ID")
    private BasicInsertion basicInsertion;

}
