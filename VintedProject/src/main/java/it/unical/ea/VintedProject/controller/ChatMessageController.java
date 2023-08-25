package it.unical.ea.VintedProject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.data.service.interfaces.ChatMessageService;
import it.unical.ea.VintedProject.dto.ChatDto;
import it.unical.ea.VintedProject.dto.NewMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "ChatMessage") //Name displayed on swagger
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/message/user/{id}")
    public ResponseEntity<List<ChatMessage>> allChatUser(@PathVariable("id")Long id){
        return ResponseEntity.ok(chatMessageService.allChatMessageByUserId(id));
    }

    @GetMapping("/message/{id}/{id2}")
    public ResponseEntity<List<ChatMessage>> allChatMessage(@PathVariable("id") Long id, @PathVariable("id2") Long id2){
        return ResponseEntity.ok(chatMessageService.allMessageByUserId(id,id2));
    }

    @PostMapping("/message/insert")
    public ResponseEntity<String> insertMessage(@RequestBody NewMessageDto newMessageDto){
        chatMessageService.insertMessageChat(newMessageDto);
        return ResponseEntity.ok("ok");
    }

}
