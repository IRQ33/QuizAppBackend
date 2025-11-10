package com.irq3.quizApp.auth.repositories;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshRepository extends JpaRepository<JwtRefreshToken, Long> {
    JwtRefreshToken getJwtRefreshTokenById(long id);

    JwtRefreshToken getJwtRefreshTokenByToken(String token);

    @Query(value = "SELECT r FROM JwtRefreshToken r WHERE r.user_id = :id")
    JwtRefreshToken findByUserId(@Param("id") long id);

    boolean existsByToken(String token);
}
