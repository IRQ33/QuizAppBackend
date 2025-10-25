package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    long id;
    String name;
    Date createdAt;
    public UserInfo(User user){
        this.id = user.getId();;
        this.name = user.getUserName();
        this.createdAt=user.getCreatedAt();
    }
}
