package com.irq3.quizApp.auth;

import com.irq3.quizApp.auth.controllers.UserController;
import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class CreateUser {
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired UserController userController;

    //wrong email
    @Test
     void testMail(){
        UserCreate userCreate = new UserCreate("a","test","12345678");

    }

    //wrong password
    @Test
    void testPassword(){

    }
    //wrong username




}
