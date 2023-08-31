package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class ChatDto {
    private Long id;
    private Long user1;
    private Long user2;
    private ChatMessage chatMessage;
    private String user1NameLastname;
    private String user2NameLastname;
    private Long insertionId;
    private String InsertionTitle;
}
