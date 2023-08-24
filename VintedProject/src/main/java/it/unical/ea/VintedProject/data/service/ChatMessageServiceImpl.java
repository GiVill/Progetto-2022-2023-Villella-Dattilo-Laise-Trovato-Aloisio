package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.dao.ChatMessageDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.ChatMessageService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatDao chatDao;
    private final ChatMessageDao chatMessageDao;
    private final UserDao userDao;
    private final MessageLang messageLang;
    private final ModelMapper modelMapper;

    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageDao.save(chatMessage);

    }

    @Override
    public void update(ChatMessage chatMessage) {
        //da vedere se serve
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        chatMessageDao.delete(chatMessage);
    }

    @Override
    public List<Chat> allChatByUserId(Long id) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(id)){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id));
        }
        List<Chat> list =  chatDao.findByReciverOrSenderOrderByBasicInsertion(id, id);


        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }

        Set<Chat> uniqueChatMessages = new HashSet<>(list);
        List<Chat> uniqueList = new ArrayList<>(uniqueChatMessages);
        return uniqueList;
    }

    @Override
    public List<ChatMessage> allChatByUserId2(Long id) {
        return null;
    }

    @Override
    public List<ChatDto> allMessageByUserId(Long id1, Long id2) {
        return null;
    }


/*
    @Override
    public List<ChatDto> allMessageByUserId(Long id1, Long id2) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(id1)){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id1));
        }
        List<ChatDto> list =  chatDao.findBySenderAndReciverOrderByDateAsc(id1, id2).stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());

        //TODO Verificare se funziona (Setta l'ultimo messaggio caricato come seen=true)
        List<ChatDto> list2 = chatDao.findByReciverAndSenderOrderByDateAsc(id2, id1)
                .stream()
                .map(s -> {
                    ChatDto chatDto = modelMapper.map(s, ChatDto.class);
                    String mex =s.getMessage();
                    ChatDto message = list.get(list.size()-1);
                    System.out.println(mex.equals(message.getMessage()));
                    System.out.println(!list.isEmpty());
                    if (!list.isEmpty() && mex.equals(message.getMessage())) {
                        System.out.println("ENTRO");
                        message.setSeen(true);
                        s.setSeen(true);
                        chatDao.save(s);
                    }
                    System.out.println(s+" "+list.get(list.size()-1));
                    return chatDto;
                })
                .collect(Collectors.toList());


        List<ChatDto> unite = new ArrayList<ChatDto>();

        unite.addAll(list);
        unite.addAll(list2);

        Collections.sort(unite, new ChatDtoComparator());


        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present",id1));
        }
        return unite;
    }
    */


    @Override
    public void insertMessageChat(NewMessageDto newMessageDto) {
        //Chat chat = modelMapper.map(newMessageDto, Chat.class);

        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(newMessageDto.getSender())){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",newMessageDto.getSender()));
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(newMessageDto.getSender());
        chatMessage.setReciver(newMessageDto.getReciver());
        chatMessage.setNickname(newMessageDto.getNickname());
        chatMessage.setMessage(newMessageDto.getMessage());
        chatMessage.setDate(LocalDateTime.now());
        chatMessageDao.save(chatMessage);
    }
}