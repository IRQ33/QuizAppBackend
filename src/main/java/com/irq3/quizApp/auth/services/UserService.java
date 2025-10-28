package com.irq3.quizApp.auth.services;


import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmail;
import com.irq3.quizApp.auth.dto.requests.UserLoginName;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.dto.response.UserLogin;
import com.irq3.quizApp.auth.utils.Result;

import java.util.Map;

public interface UserService {
    Result<UserInfo,String> createUser(UserCreate userCreate);
    long countOfUsers();
    Result<UserLogin,String> getRefreshToken(UserLoginEmail userLoginEmail);
    Result<UserLogin,String> getRefreshToken(UserLoginName userLoginName);
    Result<UserLogin,String> getRefreshToken(Map<String, Object> rawBody);
    Result<UserLogin,String> getAccesToken(String refreshToken);



}
