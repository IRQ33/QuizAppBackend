package com.irq3.quizApp.auth.repositories;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshRepository extends JpaRepository<JwtRefreshToken,Long> {
    JwtRefreshToken getJwtRefreshTokenById(long id);
    JwtRefreshToken getJwtRefreshTokenByToken(String token);
    boolean existsByToken(String token);
}
