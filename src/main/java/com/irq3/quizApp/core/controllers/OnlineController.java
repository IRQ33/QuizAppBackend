package com.irq3.quizApp.core.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineController {

    @GetMapping("/online")
    public ResponseEntity<String> isOnline(){
        return ResponseEntity.ok("Server is online");
    }
}
