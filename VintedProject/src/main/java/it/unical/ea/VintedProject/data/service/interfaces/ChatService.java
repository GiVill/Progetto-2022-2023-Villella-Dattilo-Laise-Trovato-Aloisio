package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;

import java.util.List;

public interface ChatService {

    void save (Chat chat);

    void update(Chat chat);

    void delete(Chat chat);

    List<Chat> allChatByUserId(Long id);

    List<Chat> allChatByUserId2(Long id);

    List<ChatDto> allMessageByUserId(Long id1, Long id2);


}
