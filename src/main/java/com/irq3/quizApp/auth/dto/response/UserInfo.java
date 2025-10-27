package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInfo {
    long id;
    String name;
    Date createdAt;
    public UserInfo(User user){
        this.id = user.getId();
        this.name = user.getUserName();
        this.createdAt=user.getCreatedAt();
    }

}
