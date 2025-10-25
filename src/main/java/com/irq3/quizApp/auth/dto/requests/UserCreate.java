package com.irq3.quizApp.auth.dto.requests;

import com.irq3.quizApp.auth.validators.EmailAvailable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreate {
    @Size(max = 255, message = "Your name is too big")
    String name;
    @NotBlank
    @EmailAvailable
    @Email(message = "Mail have to be valid")
    String email;
    @Size(min = 8,max = 255, message = "Your password have to have minimum 8 characters")
    String password;

    public UserCreate(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserCreate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
