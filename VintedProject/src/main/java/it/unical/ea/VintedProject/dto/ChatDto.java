package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
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
    private String user2;
    private ChatMessage chatMessage;
    private BasicInsertion basicInsertion;
}
