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
import java.util.Objects;
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

    @Column(name = "sender")
    private Long sender;

    @Column(name = "reciver")
    private Long reciver;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "seen")
    private Boolean seen=false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return Objects.equals(reciver, chat.reciver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reciver);
    }
}

/*  Per avere gli id invece che gli utenti
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
*/
