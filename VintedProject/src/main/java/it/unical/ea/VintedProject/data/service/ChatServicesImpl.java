package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
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
    private final BasicInsertionService basicInsertionService;


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
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null || !u.get().getId().equals(chat.getUser1()) || !u.get().getId().equals(chat.getUser2()) ){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present",u.get().getId()));
        }
        chatDao.delete(chat);
    }

    @Override
    public List<ChatDto> allChatByUserId(Long id) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        //if(u.get().getEmail() == null || !u.get().getId().equals(id)){
        //    throw new EntityNotFoundException(messageLang.getMessage("user.not.present",id));
        //}
        System.out.println("mlmlml");
        List<Chat> list = chatDao.findAllChatsForUser(id);
        System.out.println(list);
        //List<Chat> list = chatDao.findAllByBasicInsertionId(id);

        //List<Chat> list =  chatDao.findByUser1OrUser2OrderByBasicInsertion(id, id);
        if(list.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }

        //Set<Chat> uniqueChatMessages = new HashSet<>(list);
        //List<Chat> uniqueList = new ArrayList<>(uniqueChatMessages);
        return list.stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addChat(NewChatDto chat) {
        Optional<User> u = userDao.findUserByEmail(LoggedUserDetail.getInstance().getEmail());
        if(u.get().getEmail() == null){
            throw new EntityNotFoundException(messageLang.getMessage("user.not.present"));
        }

         chatMessageService.insertNewChat(chat);
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


}
