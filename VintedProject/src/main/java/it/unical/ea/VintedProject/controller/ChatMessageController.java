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


    @PostMapping("/message/insert")
    public ResponseEntity<String> insertMessage(@RequestBody NewMessageDto newMessageDto){
        chatMessageService.insertMessageChat(newMessageDto);
        return ResponseEntity.ok("ok");
    }

}
