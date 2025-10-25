package com.irq3.quizApp.auth.controllers;

import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.auth.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<Result<UserInfo, String>> createUser(@Valid @RequestBody UserCreate userCreate, BindingResult br){
    //TODO: make better system for it
//        if(br.hasErrors()){
//            return ResponseEntity.badRequest().body(Result.resultError(br.getAllErrors()));
//        }

        return ResponseEntity.ok(userService.createUser(userCreate));
    }
    @GetMapping("count")
    public ResponseEntity<Long> countUsers(){
        return ResponseEntity.ok(userService.countOfUsers());
    }



}
