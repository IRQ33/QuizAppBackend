package com.irq3.quizApp.admin.controllers;

import com.irq3.quizApp.admin.dto.requests.InteractionQuizRequest;
import com.irq3.quizApp.admin.dto.requests.InteractionRequest;
import com.irq3.quizApp.admin.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/admin/")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("ban_user")
    public ResponseEntity<?> banUser(@RequestBody @Valid InteractionRequest interactionRequest){
        return ResponseEntity.ok(adminService.banUser(interactionRequest));
    }
    @PutMapping("give_admin")
    public ResponseEntity<?> giveAdmin(@RequestBody @Valid InteractionRequest interactionRequest){
        return ResponseEntity.ok(adminService.giveAdmin(interactionRequest));
    }

    @PutMapping("restrict_quiz")
    public ResponseEntity<?> restrictQuiz(@RequestBody @Valid InteractionQuizRequest interactionRequest){
        return ResponseEntity.ok(adminService.restrictQuiz(interactionRequest));
    }




}
