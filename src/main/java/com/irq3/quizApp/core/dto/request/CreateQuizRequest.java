package com.irq3.quizApp.core.dto.request;

import com.irq3.quizApp.core.models.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class CreateQuizRequest {
    @NotBlank
    @Size(min = 1, max = 255, message = "Bad size of name")
    String name;
    @NotBlank
    String description;
    List<Question> content;

}
