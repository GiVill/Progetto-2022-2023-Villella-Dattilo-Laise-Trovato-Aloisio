package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(value = {AuditingEntityListener.class, ChatListener.class})
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long id;

    @ManyToMany
    @JoinTable(name = "CHAT_SENDER",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> idUser1;

    // Relazione ManyToMany tra Chat e User per i receiver
    @ManyToMany
    @JoinTable(name = "CHAT_RECEIVER",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> idUser2;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;


}
