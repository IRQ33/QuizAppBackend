package com.irq3.quizApp.core.dto.response;

import com.irq3.quizApp.core.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuizInfoResponse {
    long id;
    String name;
    String description;
    List<Question> content;
    long user_id;

}
