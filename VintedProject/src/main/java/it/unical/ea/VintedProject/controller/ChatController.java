package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.entities.Chat;
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

        @GetMapping("/chat/user/{id}")
        public ResponseEntity<List<Chat>> allChatUser(@PathVariable("id")Long id){
            return ResponseEntity.ok(chatService.allChatByUserId(id));
        }

        @GetMapping("/chat/message/{id}/{id2}")
        public ResponseEntity<List<ChatDto>> allChatMessage(@PathVariable("id") Long id, @PathVariable("id2") Long id2){
            return ResponseEntity.ok(chatService.allMessageByUserId(id,id2));
        }
}
