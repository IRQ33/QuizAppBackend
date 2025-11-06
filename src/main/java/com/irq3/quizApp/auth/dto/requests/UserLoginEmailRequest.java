package com.irq3.quizApp.auth.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor public class UserLoginEmailRequest {
    @Email
    private String email;
    @Size(min = 8, max = 255)
    private String password;

}
