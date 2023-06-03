package com.healthapp.healthapp.entity.dto;

import com.healthapp.healthapp.entity.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageToChat {
    //объект для понимания в какой чат какое сообщение отправлять
    private Message message;
    private Long chatId;
}
