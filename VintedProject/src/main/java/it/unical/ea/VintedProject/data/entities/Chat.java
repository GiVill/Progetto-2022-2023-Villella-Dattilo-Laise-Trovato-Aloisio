package it.unical.ea.VintedProject.data.entities;


import it.unical.ea.VintedProject.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(name = "user1")
    private User idUser1;

    @Column(name = "user2")
    private User idUser2;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

}
