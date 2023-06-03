package com.healthapp.healthapp.entity.dto;

import com.healthapp.healthapp.entity.Message;
import com.healthapp.healthapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonalCouncilChat {
    //объект для создания чата
    private Message message;
    private List<User> members;
}
