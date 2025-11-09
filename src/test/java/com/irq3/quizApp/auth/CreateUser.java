package com.irq3.quizApp.auth;

import com.irq3.quizApp.auth.controllers.UserController;
import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootTest
@Transactional
@Disabled
class CreateUser {
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired UserController userController;

    //wrong email
    @Disabled
    @Test
     void testMail(){
        UserCreateRequest userCreateRequest = new UserCreateRequest("a","test","12345678", LocalDate.now());

    }

    //wrong password
    @Test
    void testPassword(){

    }
    //wrong username




}
