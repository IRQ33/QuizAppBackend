package com.irq3.quizApp.auth.services;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.utils.ResultCode;

public interface JwtService {
    String generateToken(String refreshToken);
    ResultCode<String,RuntimeException> getEmail(String token);
    ResultCode<User,String> getUser(String token);
}
