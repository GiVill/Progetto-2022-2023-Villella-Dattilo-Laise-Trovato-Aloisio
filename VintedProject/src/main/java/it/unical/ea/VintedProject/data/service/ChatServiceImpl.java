package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.compare.ChatDtoComparator;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.service.interfaces.ChatService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatDao chatDao;
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
       List<Chat> list =  chatDao.findAllByIdUser1OrderByDateAsc(id);
        if(list.isEmpty()){
            messageLang.getMessage("user.order.not.present",id);
        }
        return list;
    }

    @Override
    public List<Chat> allChatByUserId2(Long id) {
        List<Chat> list =  chatDao.findAllByIdUser2OrderByDateAsc(id);
        if(list.isEmpty()){
            messageLang.getMessage("user.order.not.present",id);
        }
        return list;
    }

    @Override
    public List<ChatDto> allMessageByUserId(Long id1, Long id2) {
        List<ChatDto> list =  chatDao.findByIdUser1AndIdUser2OrderByDateAsc(id1, id2).stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());
        List<ChatDto>  list2 = chatDao.findByIdUser2AndIdUser1OrderByDateAsc(id1,id2).stream().map(s -> modelMapper.map(s, ChatDto.class)).collect(Collectors.toList());

        List<ChatDto> unite = new ArrayList<ChatDto>();

        unite.addAll(list);
        unite.addAll(list2);

        Collections.sort(unite, new ChatDtoComparator());


        if(list.isEmpty()){
            messageLang.getMessage("user.order.not.present",id1);
        }
        return unite;
    }

    @Override
    public void insertMessageChat(NewMessageDto newMessageDto) {
        //Chat chat = modelMapper.map(newMessageDto, Chat.class);
        Chat chat = new Chat();
        chat.setIdUser1(newMessageDto.getIdUser1());
        chat.setIdUser2(newMessageDto.getIdUser2());
        chat.setMessage(newMessageDto.getMessage());
        chat.setDate(LocalDateTime.now());
        chatDao.save(chat);
    }
}