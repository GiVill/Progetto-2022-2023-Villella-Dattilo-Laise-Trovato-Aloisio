package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.dao.ChatDao;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.User;
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

    @GetMapping("/chat/user/chat")
    public ResponseEntity<List<Chat>> allChatUser(@RequestBody User user){
        return ResponseEntity.ok(chatService.allChatByUserId(user));
    }

    @GetMapping("/chat/message/message")
    public ResponseEntity<List<ChatDto>> allChatMessage(@RequestBody User user, @RequestBody User user2){
        return ResponseEntity.ok(chatService.allMessageByUserId(user,user2));
    }

    @PostMapping("/chat/new")
    public ResponseEntity<String> insertMessage(@RequestBody NewMessageDto newMessageDto){
        chatService.insertMessageChat(newMessageDto);
        return ResponseEntity.ok("ok");
    }

}
