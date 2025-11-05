package com.irq3.quizApp.auth.services;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.utils.Result;

public interface RefreshJwtService {
    String generateToken(User email);
    String getEmail(String token);
    Result<User,String> getUser(String token);
}
