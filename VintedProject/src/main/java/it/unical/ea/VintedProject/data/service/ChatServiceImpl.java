package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.compare.ChatDtoComparator;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.ChatService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.list;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatDao chatDao;
    private final UserDao userDao;
    private final MessageLang messageLang;
    private final ModelMapper modelMapper;

    @Override
    public void save(Chat chat) {
        chatDao.save(chat);

    }

    @Override
    public void update(Chat chat) {
        //da vedere se serve
    }

    @Override
    public void delete(Chat chat) {
        chatDao.delete(chat);
    }

    @Override
    public List<Chat> allChatByUserId(Long id) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(id)){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id));
        }
        List<Chat> list =  chatDao.findAllBySenderOrderByDateAsc(id);


        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }

        Set<Chat> uniqueChats = new HashSet<>(list);
        List<Chat> uniqueList = new ArrayList<>(uniqueChats);
        uniqueList.sort(Comparator.comparing(Chat::getDate));
        for(Chat i : uniqueList){
            System.out.println(i.getReciver());
        }
        return uniqueList;
    }

    //TODO si puo cancellare
    @Override
    public List<Chat> allChatByUserId2(Long id) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(id)){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id));
        }
        List<Chat> list =  chatDao.findAllByReciverOrderByDateAsc(id);
        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }

        Set<Chat> uniqueChats = new HashSet<>(list);
        List<Chat> uniqueList = new ArrayList<>(uniqueChats);
        uniqueList.sort(Comparator.comparing(Chat::getDate));
        for(Chat i : uniqueList){
            System.out.println(i.getId());
        }
        return list;
    }

    @Override
    public List<ChatDto> allMessageByUserId(Long id1, Long id2) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(id1)){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id1));
        }
        List<ChatDto> list =  chatDao.findBySenderAndReciverOrderByDateAsc(id1, id2).stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());

        //TODO Verificare se funziona (Setta l'ultimo messaggio caricato come seen=true)
        List<ChatDto> list2 = chatDao.findByReciverAndSenderOrderByDateAsc(id1, id2)
                .stream()
                .map(s -> {
                    ChatDto chatDto = modelMapper.map(s, ChatDto.class);
                    if (!list.isEmpty() && s.equals(list.get(list.size() - 1))) {
                        chatDto.setSeen(true);
                        s.setSeen(true);
                        chatDao.save(s);
                    }
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

    @Override
    public void insertMessageChat(NewMessageDto newMessageDto) {
        //Chat chat = modelMapper.map(newMessageDto, Chat.class);

        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(newMessageDto.getSender())){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",newMessageDto.getSender()));
        }

        Chat chat = new Chat();
        chat.setSender(newMessageDto.getSender());
        chat.setReciver(newMessageDto.getReciver());
        chat.setNickname(newMessageDto.getNickname());
        chat.setMessage(newMessageDto.getMessage());
        chat.setDate(LocalDateTime.now());
        chatDao.save(chat);
    }
}