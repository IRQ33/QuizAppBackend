package com.irq3.quizApp.auth.repositories;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JwtRefreshRepository extends JpaRepository<JwtRefreshToken,Long> {
    long getUserIdByToken(String token);
    Date getDateExpiredByTokenByToken(String token);
    boolean existsByToken(String token);
}
