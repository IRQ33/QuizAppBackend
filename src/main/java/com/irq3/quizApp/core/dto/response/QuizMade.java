package com.irq3.quizApp.core.dto.response;

import com.irq3.quizApp.core.models.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class QuizMade {
    private long id;
    private String name;
    public QuizMade(Quiz quiz){
        this.id = quiz.getId();
        this.name = quiz.getName();
    }


}
