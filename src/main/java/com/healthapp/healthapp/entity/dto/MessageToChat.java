package com.healthapp.healthapp.entity.dto;

import com.healthapp.healthapp.entity.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageToChat {
    private Message message;
    private Long chatId;
}
