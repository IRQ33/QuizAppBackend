package com.irq3.quizApp.auth.dto.requests;

import com.irq3.quizApp.auth.validators.UniqueEmail;
import com.irq3.quizApp.auth.validators.UniqueName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter public class UserCreateRequest {
    @Size(max = 255, message = "Your name is too big")
    @UniqueName
    String name;
    @NotBlank
    @UniqueEmail
    @Email(message = "Mail have to be valid")
    String email;
    @Size(min = 8,max = 255, message = "Your password have to have minimum 8 characters")
    String password;

    public UserCreateRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserCreateRequest() {
    }

}
