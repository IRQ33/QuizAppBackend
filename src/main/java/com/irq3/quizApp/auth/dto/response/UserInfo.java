package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
