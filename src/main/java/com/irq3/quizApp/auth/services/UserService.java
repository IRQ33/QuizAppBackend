package com.irq3.quizApp.auth.services;


import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.utils.Result;

public interface UserService {
    Result<UserInfo,String> createUser(UserCreate userCreate);
    long countOfUsers();



}
