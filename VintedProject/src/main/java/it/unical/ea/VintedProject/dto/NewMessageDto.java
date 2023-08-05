package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.data.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NewMessageDto {

    private User idUser1;
    private User idUser2;
    private String message;
}
