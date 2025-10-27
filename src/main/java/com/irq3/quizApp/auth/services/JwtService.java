package com.irq3.quizApp.auth.services;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.utils.Result;

public interface JwtService {
    String generateToken(String refreshToken);
    String getEmail(String token);
    Result<User,String> getUser(String token);
}
