package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfoResponse {
    long id;
    String name;
    LocalDateTime createdAt;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.createdAt = user.getCreatedAt();
    }

}
