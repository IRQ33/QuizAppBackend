package com.irq3.quizApp.admin.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class InteractionRequest {
    @NotNull(message = "This value cannot be empty")
    long userId;
    @NotNull(message = "This value cannot be empty")
    @Size(min = 10, max = 200)
    String reason;
}
