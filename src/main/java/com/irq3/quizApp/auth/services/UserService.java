package com.irq3.quizApp.auth.services;


import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmailRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginNameRequest;
import com.irq3.quizApp.auth.dto.response.UserInfoResponse;
import com.irq3.quizApp.auth.dto.response.UserLoginResponse;
import com.irq3.quizApp.utils.ResultCode;

import java.util.Map;

public interface UserService {
    ResultCode<UserInfoResponse, String> createUser(UserCreateRequest userCreateRequest);

    ResultCode<String, String> deleteUser(long id);

    UserInfoResponse getUser(long id);

    long countOfUsers();

    ResultCode<UserLoginResponse, String> getRefreshToken(UserLoginEmailRequest userLoginEmailRequest);

    ResultCode<UserLoginResponse, String> getRefreshToken(UserLoginNameRequest userLoginNameRequest);

    ResultCode<UserLoginResponse, String> getRefreshToken(Map<String, Object> rawBody);

    ResultCode<UserLoginResponse, String> getAccesToken(String refreshToken);


}
