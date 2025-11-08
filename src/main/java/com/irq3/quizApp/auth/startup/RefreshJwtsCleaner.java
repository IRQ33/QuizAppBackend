package com.irq3.quizApp.auth.startup;

import com.irq3.quizApp.auth.services.RefreshJwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class RefreshJwtsCleaner implements CommandLineRunner {

    private final RefreshJwtService refreshJwtService;

    RefreshJwtsCleaner(RefreshJwtService refreshJwtService) {
        this.refreshJwtService = refreshJwtService;
    }

    @Override public void run(String... args) throws Exception {
        refreshJwtService.deleteExpiredTokens();
    }
}
