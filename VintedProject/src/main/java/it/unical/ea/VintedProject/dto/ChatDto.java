package it.unical.ea.VintedProject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class ChatDto {
    private Long id;
    private Long idUser1;
    private String message;
    private LocalDateTime date;
}
