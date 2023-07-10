package it.unical.ea.VintedProject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NewMessageDto {

    private Long idUser1;
    private Long idUser2;
    private String message;
}
