package com.irq3.quizApp.auth.controllers;

import com.irq3.quizApp.auth.dto.requests.UserAccessTokenRequest;
import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.dto.response.UserInfoResponse;
import com.irq3.quizApp.auth.dto.response.UserLoginResponse;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.utils.Result;
import com.irq3.quizApp.utils.ResultCode;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserService userService;


    UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("register")
    public ResponseEntity<Result<UserInfoResponse, String>> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest, BindingResult br){
        return ResponseEntity.ok(userService.createUser(userCreateRequest));
    }

    @Transactional(readOnly = true)
    @GetMapping("count")
    public ResponseEntity<Long> countUsers(){
        return ResponseEntity.ok(userService.countOfUsers());
    }

    @Transactional
    @PostMapping("login")
    public ResponseEntity<Result<UserLoginResponse,String>> loginUser(@RequestBody Map<String,Object> data){
        return ResponseEntity.ok(userService.getRefreshToken(data));
    }

    @Transactional
    @PostMapping(value = "access",consumes = "application/json")
    public ResponseEntity<Result<UserLoginResponse,String>> getAccesToken(@RequestBody UserAccessTokenRequest token){
        LoggerFactory.getLogger("app").debug(token.token());
        return ResponseEntity.ok(userService.getAccesToken(token.token()));
    }

    @Transactional
    @DeleteMapping("remove/{id}")
    public ResponseEntity<ResultCode<String, String>> removeUser(@PathVariable("id") long id){
        var delete = userService.deleteUser(id);
        return ResponseEntity.status(delete.status()).body(delete);
    }



}
