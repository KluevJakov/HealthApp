package com.healthapp.healthapp.service;

import com.healthapp.healthapp.entity.Chat;
import com.healthapp.healthapp.entity.Message;
import com.healthapp.healthapp.entity.dto.MessageToChat;
import com.healthapp.healthapp.entity.dto.PersonalCouncilChat;
import com.healthapp.healthapp.repository.ChatRepository;
import com.healthapp.healthapp.repository.MessageRepository;
import com.healthapp.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> create(PersonalCouncilChat request) { //создание чата
        Chat chat = new Chat();
        chat.setMembers(new ArrayList<>());
        chat.setMessages(new ArrayList<>());

        if (request.getMembers() == null || request.getMembers().isEmpty()) { //если участники не указаны
            chat.getMembers().add(userRepository.findFreeDoctor()); //значит это пользователь пытается создать чат из "Узнать о здоровье"
        } else {
            request.getMembers().forEach(e -> { //или указываем всех участников чата
                chat.getMembers().add(e);
            });
        }
        Message message = request.getMessage();
        message.setDate(new Date()); //выставляем дату отправки сообщения
        Message initMessage = messageRepository.save(message); //сохраняем сообщение в бд

        chat.getMessages().add(initMessage); //добавляем в чат
        chat.getMembers().add(initMessage.getSender()); //добавляем участником чата отправителя (инициатора)

        Chat createdChat = chatRepository.save(chat); //сохраняем чат со всеми данными
        return ResponseEntity.ok().body(createdChat); //возвращаем созданный чат
    }

    public ResponseEntity<List<Chat>> getByMember(Long id) {
        return ResponseEntity.ok().body(chatRepository.findByMember(id));
    }

    public ResponseEntity<?> send(MessageToChat message) {
        Message newMessage = message.getMessage();
        Chat chat = chatRepository.findById(message.getChatId()).get(); //ищем по id чат в который направляем новое сообщение
        newMessage.setDate(new Date()); //выставляем дату отправки
        Message persistedMessage = messageRepository.save(newMessage); //сохраняем сообщение в бд
        chat.getMessages().add(persistedMessage); //добавляем в чат
        chatRepository.save(chat); //мерджим существующую запись чата с обновленной
        return ResponseEntity.ok().body("");
    }

    public Chat findById(Long id) {
        Chat chat = chatRepository.findById(id).get();
        chat.setMessages(chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getDate).reversed()) //фильтруем по дате отправки
                .collect(Collectors.toList()));
        return chat;
    }

    public ResponseEntity<?> deleteById(Long id) {
        chatRepository.deleteById(id);
        return ResponseEntity.ok("");
    }
}