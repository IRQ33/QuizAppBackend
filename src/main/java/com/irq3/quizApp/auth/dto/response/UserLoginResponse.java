package com.irq3.quizApp.auth.dto.response;

import com.irq3.quizApp.auth.enums.Tokens;
import com.irq3.quizApp.auth.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse extends UserInfoResponse {
    private String token;
    private Tokens tokenType;

    public UserLoginResponse(User user, String token, Tokens tokenType) {
        super(user);
        this.token = token;
        this.tokenType = tokenType;
    }

}
