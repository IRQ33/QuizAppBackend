package com.irq3.quizApp.core.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class CreateQuiz {
    @NotBlank
    @Size(min = 1,max = 255, message = "Bad size of name")
    String name;
    @NotBlank
    String description;
    @Size(min = 20)
    String content;

}
