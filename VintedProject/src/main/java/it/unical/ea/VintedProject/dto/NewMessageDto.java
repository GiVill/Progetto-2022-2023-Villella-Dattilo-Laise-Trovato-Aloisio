package it.unical.ea.VintedProject.dto;

import it.unical.ea.VintedProject.data.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NewMessageDto {

    private Long sender;
    private Long reciver;
    private String nickname;
    private String message;
}
