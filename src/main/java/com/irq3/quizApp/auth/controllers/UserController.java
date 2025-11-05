package com.irq3.quizApp.auth.controllers;

import com.irq3.quizApp.auth.dto.requests.UserAccessToken;
import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.dto.response.UserLogin;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.utils.Result;
import com.irq3.quizApp.utils.ResultCode;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("register")
    public ResponseEntity<Result<UserInfo, String>> createUser(@Valid @RequestBody UserCreate userCreate, BindingResult br){


        return ResponseEntity.ok(userService.createUser(userCreate));
    }
    @GetMapping("count")
    public ResponseEntity<Long> countUsers(){
        return ResponseEntity.ok(userService.countOfUsers());
    }

    @PostMapping("login")
    public ResponseEntity<Result<UserLogin,String>> loginUser(@RequestBody Map<String,Object> data){
        return ResponseEntity.ok(userService.getRefreshToken(data));
    }


    @PostMapping(value = "access",consumes = "application/json")
    public ResponseEntity<Result<UserLogin,String>> getAccesToken(@RequestBody UserAccessToken token){
        LoggerFactory.getLogger("app").debug(token.token());
        return ResponseEntity.ok(userService.getAccesToken(token.token()));
    }
    @DeleteMapping("remove/{id}")
    public ResponseEntity<ResultCode<String, String>> removeUser(@PathVariable("id") long id){
        var delete = userService.deleteUser(id);
        return ResponseEntity.status(delete.status()).body(delete);
    }



}
