package com.irq3.quizApp.core.dto.response;

import lombok.Data;

@Data
public class QuizInfoResponse {
    long id;
    String name;
    String description;
    String content;
    long user_id;

}
