package it.unical.ea.VintedProject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@ToString
public class ChatMessageDto {
    private Long id;
    private Long reciver;
    private String message;
    private LocalDateTime date;
    private Boolean seen;
    private Long chatId;
}
