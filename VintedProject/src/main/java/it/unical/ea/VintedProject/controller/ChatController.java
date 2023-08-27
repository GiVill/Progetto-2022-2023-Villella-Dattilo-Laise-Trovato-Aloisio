package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.data.service.interfaces.ChatMessageService;
import it.unical.ea.VintedProject.data.service.interfaces.ChatService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Chat") //Name displayed on swagger
public class ChatController {

        private final ChatService chatService;
        private final ChatMessageService chatMessageService;

        @GetMapping("/chat/user/{id}")
        public ResponseEntity<List<Chat>> allChatUser(@PathVariable("id")Long id){
            System.out.println(chatService.allChatByUserId(id));
            return ResponseEntity.ok(chatService.allChatByUserId(id));
        }

        @GetMapping("/chat/message/{chatId}")
        public ResponseEntity<List<ChatMessage>> allChatMessage(@PathVariable("chatId") Long chatId){
            return ResponseEntity.ok(chatMessageService.allChatMessageByChat_id(chatId));
        }
}
