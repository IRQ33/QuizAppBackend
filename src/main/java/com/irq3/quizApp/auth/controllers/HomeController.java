package com.irq3.quizApp.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/")
@PreAuthorize("hasRole('OWNER')")
@RestController()
class HomeController {
    @GetMapping("/")
    ResponseEntity<?> home(){

        return ResponseEntity.ok("OK");
    }
}
