package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;

import java.util.List;

public interface ChatMessageService {

    void save (ChatMessage chatMessage);

    void update(ChatMessage chatMessage);

    void delete(ChatMessage chatMessage);

    List<ChatMessage> allChatMessageByUserId(Long id);

    List<ChatMessage> allChatByUserId2(Long id);

    List<ChatMessage> allMessageByUserId(Long id1, Long id2);

    void insertMessageChat(NewMessageDto newMessageDto);
}
