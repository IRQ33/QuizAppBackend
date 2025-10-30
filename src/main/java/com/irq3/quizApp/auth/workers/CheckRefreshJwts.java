package com.irq3.quizApp.auth.workers;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Async
@Component
class CheckRefreshJwts {
    private final JwtRefreshRepository jwtRefreshRepository;

    CheckRefreshJwts(JwtRefreshRepository jwtRefreshRepository) {
        this.jwtRefreshRepository = jwtRefreshRepository;
    }

    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 3)
    public void deleteExpiredTokens(){
        List<JwtRefreshToken> list = jwtRefreshRepository.findAll();
        for (var t : list){
            if(t.getDateExpired().getTime()<new Date().getTime()){
                jwtRefreshRepository.delete(t);
            }
        }
    }
}
