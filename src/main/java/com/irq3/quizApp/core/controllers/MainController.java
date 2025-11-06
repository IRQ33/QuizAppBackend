package com.irq3.quizApp.core.controllers;

import com.irq3.quizApp.core.dto.request.CreateQuizRequest;
import com.irq3.quizApp.core.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post/")
class MainController {
    private final QuizService quizService;

    MainController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreateQuizRequest quiz){
        var create = quizService.createQuiz(quiz);
        return ResponseEntity.status(create.status()).body(create);
    }

}
