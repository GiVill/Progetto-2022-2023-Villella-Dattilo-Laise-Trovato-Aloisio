package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.service.interfaces.ChatMessageService;
import it.unical.ea.VintedProject.data.service.interfaces.ChatService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewChatDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatServicesImpl implements ChatService {


    private final ChatDao chatDao;
    private final ChatMessageService chatMessageService;
    private final UserDao userDao;
    private final MessageLang messageLang;
    private final ModelMapper modelMapper;
    private final LoggedUserMethod loggedUserMethod;



    @Override
    public void save(Chat chat) {
        chatDao.save(chat);

    }

    @Override
    public void update(Chat chatMessage) {
        //da vedere se serve
    }

    @Override
    public void delete(Chat chat) {
        Long id = loggedUserMethod.getLoggedUserId();
        if(id.equals(chat.getUser1()) || !id.equals(chat.getUser2()) ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id));
        }
        chatDao.delete(chat);
    }

    @Override
    public List<ChatDto> allChatByUserId(Long id) {
        loggedUserMethod.checkLoggedUser(id);

        List<Chat> list = chatDao.findAllChatsForUser(id);
        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }
        return list.stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addChat(NewChatDto chat) {
        loggedUserMethod.checkLoggedUser();
         chatMessageService.insertNewChat(chat);
    }

}
