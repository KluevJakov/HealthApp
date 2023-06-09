package com.healthapp.healthapp.controller;

import com.healthapp.healthapp.entity.dto.MessageToChat;
import com.healthapp.healthapp.entity.dto.PersonalCouncilChat;
import com.healthapp.healthapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonalCouncilChat request) {
        return chatService.create(request); //создание чата
    }

    @GetMapping
    public ResponseEntity<?> chats(@RequestParam Long id) {
        return chatService.getByMember(id); //получение списка чатов по участнику
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody MessageToChat message) {
        return chatService.send(message); //обработка принятого сообщения в чате
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> chat(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.findById(id)); //получение конкретного чата по id
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return chatService.deleteById(id); //удаление чата по id
    }
}
