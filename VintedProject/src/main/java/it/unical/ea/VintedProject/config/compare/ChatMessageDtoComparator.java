package it.unical.ea.VintedProject.config.compare;

import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.ChatMessageDto;

import java.util.Comparator;

public class ChatMessageDtoComparator implements Comparator<ChatMessageDto> {
    @Override
    public int compare(ChatMessageDto chat1, ChatMessageDto chat2) {
        return chat1.getDate().compareTo(chat2.getDate());
    }
}
