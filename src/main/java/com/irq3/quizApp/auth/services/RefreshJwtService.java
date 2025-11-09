package com.irq3.quizApp.auth.services;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.utils.ResultCode;

public interface RefreshJwtService {
    String generateToken(User email);

    String getEmail(String token);

    ResultCode<User, String> getUser(String token);

    void deleteExpiredTokens();
}
