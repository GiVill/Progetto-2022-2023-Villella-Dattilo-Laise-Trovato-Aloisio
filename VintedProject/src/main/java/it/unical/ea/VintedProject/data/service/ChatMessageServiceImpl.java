package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.config.newsletter.EmailSender;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.dao.ChatMessageDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.ChatMessageService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.NewChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final EmailSender emailSender;
    private final ChatDao chatDao;
    private final ChatMessageDao chatMessageDao;
    private final UserDao userDao;
    private final MessageLang messageLang;
    private final UserService userService;
    private final BasicInsertionService basicInsertionService;
    private final LoggedUserMethod loggedUserMethod;


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
    public List<ChatMessage> allChatMessageByChat_id(Long chatId) {
        loggedUserMethod.checkLoggedUser();
        List<ChatMessage> list = chatMessageDao.findAllByChatOrderByDateAsc(chatId);
        System.out.println(list);


        if (list.isEmpty()) {
            throw new EntityNotFoundException(messageLang.getMessage("chat.not.present"));
        }

        //Set<ChatMessage> uniqueChatMessages = new HashSet<>(list);
        //List<ChatMessage> uniqueList = new ArrayList<>(uniqueChatMessages);
        return list;
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


        loggedUserMethod.getLoggedUserId(newMessageDto.getSender());

        Chat chat = chatDao.getById(newMessageDto.getChatId());

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(Long.valueOf(newMessageDto.getSender()));
        chatMessage.setReciver(Long.valueOf(newMessageDto.getReciver()));
        chatMessage.setMessage(newMessageDto.getMessage());
        chatMessage.setDate(LocalDateTime.now());



        chatMessage.setChat(chat.getId());
        chatMessageDao.save(chatMessage);


    }



    @Override
    public void insertNewChat(NewChatDto NewChatDto) {

        BasicInsertion insertion = basicInsertionService.getById(Long.valueOf(NewChatDto.getInsertionId()));
        if (chatDao.findByUser1AndUser2AndInsertionId(Long.valueOf(NewChatDto.getSender()), Long.valueOf(NewChatDto.getReciver()), Long.valueOf(NewChatDto.getInsertionId())).isEmpty()) {
            User user1 = userService.getUserById(Long.valueOf(NewChatDto.getSender()));
            User user2 = userService.getUserById(Long.valueOf(NewChatDto.getReciver()));
            Chat chat = new Chat();
            chat.setUser1(Long.valueOf(NewChatDto.getSender()));
            chat.setUser1NameLastname(user1.getFirstName() + " " + user1.getLastName());
            chat.setUser2(Long.valueOf(NewChatDto.getReciver()));
            chat.setUser2NameLastname(user2.getFirstName() + " " + user2.getLastName());
            chat.setInsertionId(Long.valueOf(NewChatDto.getInsertionId()));
            chat.setInsertionTitle(insertion.getTitle());


            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(Long.valueOf(NewChatDto.getSender()));
            chatMessage.setReciver(Long.valueOf(NewChatDto.getReciver()));
            chatMessage.setMessage(NewChatDto.getMessage());
            chatMessage.setDate(LocalDateTime.now());

            chatDao.save(chat);

            chatMessage.setChat(chat.getId());
            chatMessageDao.save(chatMessage);
            System.out.println(chat);
            System.out.println(chatMessage);
            emailSender.sendSimpleEmail(user2.getEmail(),"Ocarina Coders","Hai una nuova chat!");

        } else {

            Optional<Chat> chat = chatDao.findByUser1AndUser2AndInsertionId(Long.valueOf(NewChatDto.getSender()), Long.valueOf(NewChatDto.getReciver()), Long.valueOf(NewChatDto.getInsertionId()));
            Chat newChat = chat.get();

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(Long.valueOf(NewChatDto.getSender()));
            chatMessage.setReciver(Long.valueOf(NewChatDto.getReciver()));
            chatMessage.setMessage(NewChatDto.getMessage());
            chatMessage.setDate(LocalDateTime.now());
            chatMessage.setChat(newChat.getId());

            chatMessageDao.save(chatMessage);
            chatDao.save(newChat);

        }
    }
    }

