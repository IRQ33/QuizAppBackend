package com.irq3.quizApp.auth.servicesImpl;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.utils.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class RefreshJwtServiceImpl implements com.irq3.quizApp.auth.services.RefreshJwtService {
    @Value("${jwt.token}")
    String token;

    private final UserRepository userRepository;
    private final JwtRefreshRepository jwtRefreshRepository;
    SecretKey key;

    public RefreshJwtServiceImpl(UserRepository userRepository, JwtRefreshRepository jwtRefreshRepository) {
        this.userRepository = userRepository;
        this.jwtRefreshRepository = jwtRefreshRepository;
    }


    private SecretKey getKey()
    {
        if(key==null)
        {
            key = Keys.hmacShaKeyFor(token.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }


    @Override public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime()+1000*60*60*24);
        String token = Jwts.builder().subject(user.getEmail()).signWith(getKey()).expiration(expiry).issuedAt(now).compact();
        JwtRefreshToken refreshToken = JwtRefreshToken.builder()
                .user_id(user.getId())
                .dateCreated(now)
                .dateExpired(expiry)
                .token(token).build();
        jwtRefreshRepository.save(refreshToken);
        return token;
    }

    @Override public String getEmail(String token) {
        JwtParser parser = Jwts.parser().verifyWith(getKey()).build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    @Override public Result<User, String> getUser(String token) {
        Date expired = jwtRefreshRepository.getJwtRefreshTokenByToken(token).getDateExpired();
        if(new Date().getTime()>expired.getTime()|| jwtRefreshRepository.existsByToken(token)){
            return Result.resultError("Invalid Jwt");
        }
        return Result.resultOk(userRepository.getUserByEmail(getEmail(token)));
    }
}
