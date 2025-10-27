package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin extends UserInfo {
    private String token;

    public UserLogin(User user, String token) {
        super(user);
        this.token = token;
    }

}
