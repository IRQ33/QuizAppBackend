package com.irq3.quizApp.auth.controllers;

import com.irq3.quizApp.auth.dto.requests.UserAccessTokenRequest;
import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.dto.response.UserInfoResponse;
import com.irq3.quizApp.auth.dto.response.UserLoginResponse;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.utils.ResultCode;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("register")
    public ResponseEntity<ResultCode<UserInfoResponse, String>> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest, BindingResult br){
        var create = userService.createUser(userCreateRequest);
        return ResponseEntity.status(create.status()).body(create);
    }

    @Transactional(readOnly = true)
    @GetMapping("count")
    public ResponseEntity<Long> countUsers(){
        return ResponseEntity.ok(userService.countOfUsers());
    }

    @Transactional
    @PostMapping("login")
    public ResponseEntity<ResultCode<UserLoginResponse,String>> loginUser(@RequestBody Map<String,Object> data){
        var login = userService.getRefreshToken(data);
        return ResponseEntity.status(login.status()).body(login);
    }

    @Transactional
    @PostMapping(value = "access",consumes = "application/json")
    public ResponseEntity<ResultCode<UserLoginResponse,String>> getAccesToken(@RequestBody UserAccessTokenRequest token){
        var acces = userService.getAccesToken(token.token());
        return ResponseEntity.status(acces.status()).body(acces);
    }

    @Transactional
    @DeleteMapping("remove/{id}")
    public ResponseEntity<ResultCode<String, String>> removeUser(@PathVariable("id") long id){
        var delete = userService.deleteUser(id);
        return ResponseEntity.status(delete.status()).body(delete);
    }



}
