package com.irq3.quizApp.auth.workers;

import com.irq3.quizApp.auth.services.RefreshJwtService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Async
@Component
class CheckRefreshJwts {
    private final RefreshJwtService refreshJwtService;

    CheckRefreshJwts(RefreshJwtService refreshJwtService) {
        this.refreshJwtService = refreshJwtService;
    }

    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 3)
    public void deleteExpiredTokens() {
        refreshJwtService.deleteExpiredTokens();
    }
}
