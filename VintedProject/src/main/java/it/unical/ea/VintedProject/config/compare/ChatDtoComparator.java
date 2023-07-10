package it.unical.ea.VintedProject.config.compare;

import it.unical.ea.VintedProject.dto.ChatDto;

import java.util.Comparator;

public class ChatDtoComparator implements Comparator<ChatDto> {
    @Override
    public int compare(ChatDto chat1, ChatDto chat2) {
        return chat1.getDate().compareTo(chat2.getDate());
    }
}
