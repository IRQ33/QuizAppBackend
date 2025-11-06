package com.irq3.quizApp.auth.services;


import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmailRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginNameRequest;
import com.irq3.quizApp.auth.dto.response.UserInfoResponse;
import com.irq3.quizApp.auth.dto.response.UserLoginResponse;
import com.irq3.quizApp.utils.Result;
import com.irq3.quizApp.utils.ResultCode;

import java.util.Map;

public interface UserService {
    Result<UserInfoResponse,String> createUser(UserCreateRequest userCreateRequest);
    ResultCode<String, String> deleteUser(long id);
    UserInfoResponse getUser(long id);
    long countOfUsers();
    Result<UserLoginResponse,String> getRefreshToken(UserLoginEmailRequest userLoginEmailRequest);
    Result<UserLoginResponse,String> getRefreshToken(UserLoginNameRequest userLoginNameRequest);
    Result<UserLoginResponse,String> getRefreshToken(Map<String, Object> rawBody);
    Result<UserLoginResponse,String> getAccesToken(String refreshToken);



}
