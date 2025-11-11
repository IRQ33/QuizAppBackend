package com.irq3.quizApp.admin.controllers;

import com.irq3.quizApp.admin.dto.requests.InteractionQuizRequest;
import com.irq3.quizApp.admin.dto.requests.InteractionRequest;
import com.irq3.quizApp.admin.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("ban_user")
    public ResponseEntity<?> banUser(@RequestBody @Valid InteractionRequest interactionRequest){
        return ResponseEntity.ok(adminService.banUser(interactionRequest));
    }
    @PutMapping(value = "give_admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giveAdmin(@RequestBody @Valid InteractionRequest interactionRequest){
        return ResponseEntity.ok(adminService.giveAdmin(interactionRequest));
    }

    @PutMapping(value = "restrict_quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> restrictQuiz(@RequestBody @Valid InteractionQuizRequest interactionRequest){
        return ResponseEntity.ok(adminService.restrictQuiz(interactionRequest));
    }
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adminCheck(){
        return ResponseEntity.ok("You have admin");
    }




}
