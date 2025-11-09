package com.irq3.quizApp.auth.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
@Profile("dev")
class Test2Controller {
    @GetMapping("auth")
    public String test(Authentication auth) {
        return auth != null ? "Authenticated: " + auth.getName() : "Not authenticated";
    }
}
