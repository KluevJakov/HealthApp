package com.healthapp.healthapp.service;

import com.healthapp.healthapp.configuration.AppConstants;
import com.healthapp.healthapp.entity.Chat;
import com.healthapp.healthapp.entity.Message;
import com.healthapp.healthapp.entity.User;
import com.healthapp.healthapp.entity.dto.MessageToChat;
import com.healthapp.healthapp.entity.dto.PersonalCouncilChat;
import com.healthapp.healthapp.repository.ChatRepository;
import com.healthapp.healthapp.repository.MessageRepository;
import com.healthapp.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> create(PersonalCouncilChat request) {
        Chat chat = new Chat();
        chat.setMembers(new ArrayList<>());
        chat.setMessages(new ArrayList<>());

        if (request.getMembers() == null || request.getMembers().isEmpty()) {
            chat.getMembers().add(userRepository.findFreeDoctor());
        } else {
            request.getMembers().forEach(e -> {
                chat.getMembers().add(e);
            });
        }
        Message message = request.getMessage();
        message.setDate(new Date());
        Message initMessage = messageRepository.save(message);

        chat.getMessages().add(initMessage);
        chat.getMembers().add(initMessage.getSender());

        Chat createdChat = chatRepository.save(chat);
        return ResponseEntity.ok().body(createdChat);
    }

    public ResponseEntity<List<Chat>> getByMember(Long id) {
        return ResponseEntity.ok().body(chatRepository.findByMember(id));
    }

    public ResponseEntity<?> send(MessageToChat message) {
        Message newMessage = message.getMessage();
        Chat chat = chatRepository.findById(message.getChatId()).get();
        newMessage.setDate(new Date());
        Message persistedMessage = messageRepository.save(newMessage);
        chat.getMessages().add(persistedMessage);
        chatRepository.save(chat);
        return ResponseEntity.ok().body("");
    }

    public Chat findById(Long id) {
        Chat chat = chatRepository.findById(id).get();
        chat.setMessages(chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getDate).reversed())
                .collect(Collectors.toList()));
        return chat;
    }
}