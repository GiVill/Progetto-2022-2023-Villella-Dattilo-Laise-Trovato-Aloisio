package it.unical.ea.VintedProject.data.service.interfaces;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import it.unical.ea.VintedProject.dto.UserDto;

import java.util.List;

public interface ChatService {

    void save (Chat chat);

    void update(Chat chat);

    void delete(Chat chat);

    List<Chat> allChatByUserId(User user1);

    List<Chat> allChatByUserId2(User user2);

    List<ChatDto> allMessageByUserId(User id1, User id2);

    void insertMessageChat(NewMessageDto newMessageDto);
}
