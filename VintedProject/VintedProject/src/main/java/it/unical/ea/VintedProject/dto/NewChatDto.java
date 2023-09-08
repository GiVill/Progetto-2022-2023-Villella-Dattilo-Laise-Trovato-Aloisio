package it.unical.ea.VintedProject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NewChatDto {
    private Long sender;
    private Long reciver;
    private String message;
    private Long insertionId;
    private String user1NameLastname;
    private String user2NameLastname;
    private String InsertionTitle;
}
