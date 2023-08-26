package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    void save (Chat chat);

    void update(Chat chat);

    void delete(Chat chat);

    List<Chat> allChatByUserId(Long id);

    Optional<Chat> existChat(Long user1, Long user2, Long insertion);


}
