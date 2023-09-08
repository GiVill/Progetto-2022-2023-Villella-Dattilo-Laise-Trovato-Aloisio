package it.unical.ea.VintedProject.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(value = {AuditingEntityListener.class, ChatListener.class})
@Table(name = "CHATMESSAGE")
public class ChatMessage {

    @Id
    //Abbiamo lasciato GenerationType.IDENTITY per non avere problemi nel dbgenerator
    //Andrebbe cambiato con GenerationType.UUID
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

    @Column(name = "chat")
    private Long chat;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatMessage)) return false;
        ChatMessage chatMessage = (ChatMessage) o;
        return Objects.equals(reciver, chatMessage.reciver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reciver);
    }
}

