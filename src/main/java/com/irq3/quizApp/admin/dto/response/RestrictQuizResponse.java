package com.irq3.quizApp.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class RestrictQuizResponse {
    long id;
    long user_id;
    long quiz_id;
    String reason;


}
