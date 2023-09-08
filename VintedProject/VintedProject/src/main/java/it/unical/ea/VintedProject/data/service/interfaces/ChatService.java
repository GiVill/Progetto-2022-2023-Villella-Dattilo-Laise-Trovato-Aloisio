package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    void save(Chat chat);

    void delete(Chat chat);

    List<ChatDto> allChatByUserId(Long id);

    void addChat(NewChatDto chat);
}


