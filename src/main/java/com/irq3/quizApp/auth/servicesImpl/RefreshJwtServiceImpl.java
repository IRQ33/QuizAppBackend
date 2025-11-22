package com.irq3.quizApp.auth.servicesImpl;

import com.irq3.quizApp.auth.models.JwtRefreshToken;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.utils.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class RefreshJwtServiceImpl implements com.irq3.quizApp.auth.services.RefreshJwtService {
    @Value("${jwt.token}")
    String token;

    private final UserRepository userRepository;
    private final JwtRefreshRepository jwtRefreshRepository;
    SecretKey key;


    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(token.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }


    @Override public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 1000 * 60 * 60 * 24);
        String token = Jwts.builder().subject(user.getEmail()).signWith(getKey()).expiration(expiry).issuedAt(now).compact();
        JwtRefreshToken refreshToken = JwtRefreshToken.builder()
                .user_id(user.getId())
                .dateCreated(now)
                .dateExpired(expiry)
                .token(token).build();

        var tokenMade = jwtRefreshRepository.findByUserId(user.getId());

        if (tokenMade != null) {
            jwtRefreshRepository.delete(tokenMade);
        }
        jwtRefreshRepository.save(refreshToken);
        return token;
    }

    @Override public String getEmail(String token) {
        JwtParser parser = Jwts.parser().verifyWith(getKey()).build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    @Override public ResultCode<User, String> getUser(String token) {
        System.out.println(token);
        Date expired = jwtRefreshRepository.getJwtRefreshTokenByToken(token).getDateExpired();
        if (new Date().after(expired) || !jwtRefreshRepository.existsByToken(token)) {
            System.out.println(jwtRefreshRepository.existsByToken(token));
            System.out.println(new Date().getTime() > expired.getTime());
            return ResultCode.resultError("Invalid Jwt");
        }
        return ResultCode.resultOk(userRepository.getUserByEmail(getEmail(token)));
    }

    @Override public void deleteExpiredTokens() {
        List<JwtRefreshToken> list = jwtRefreshRepository.findAll();
        for (var t : list) {
            if (t.getDateExpired().getTime() < new Date().getTime()) {
                jwtRefreshRepository.delete(t);
            }
        }
    }
}
