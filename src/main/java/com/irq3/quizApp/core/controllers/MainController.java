package com.irq3.quizApp.core.controllers;

import com.irq3.quizApp.core.dto.request.CreateQuizRequest;
import com.irq3.quizApp.core.dto.request.DeleteQuizRequest;
import com.irq3.quizApp.core.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz/")
class MainController {
    private final QuizService quizService;

    MainController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Transactional
    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@Valid @RequestBody CreateQuizRequest quiz) {
        var create = quizService.createQuiz(quiz);
        return ResponseEntity.status(create.status()).body(create);
    }

    @Transactional(readOnly = true)
    @GetMapping("all")
    public ResponseEntity<?> getQuiz() {
        return ResponseEntity.ok(quizService.getAllQuizzes().o());
    }

    @Transactional
    @DeleteMapping("delete")
    public ResponseEntity<?> removeQuiz(@RequestBody DeleteQuizRequest deleteQuizRequest) {
        var delete = quizService.removeQuiz(deleteQuizRequest.id());
        return ResponseEntity.status(delete.status()).body(delete);
    }

    @Transactional(readOnly = true)
    @GetMapping("search/{name}")
    public ResponseEntity<?> searchQuizzes(@PathVariable("name") String name) {
        var search = quizService.searchQuizzes(name);
        return ResponseEntity.status(search.status()).body(search);
    }


}
