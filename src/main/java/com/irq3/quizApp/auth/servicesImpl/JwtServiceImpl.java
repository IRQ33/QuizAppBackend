package com.irq3.quizApp.auth.servicesImpl;

import com.irq3.quizApp.auth.exceptions.TokenExpiredException;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.JwtService;
import com.irq3.quizApp.auth.services.RefreshJwtService;
import com.irq3.quizApp.utils.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.token}")
    String token;
    private final RefreshJwtService refreshJwtService;
    private final JwtRefreshRepository jwtRefreshRepository;
    private final UserRepository userRepository;
    private SecretKey key;

    public JwtServiceImpl(RefreshJwtService refreshJwtService, JwtRefreshRepository jwtRefreshRepository, UserRepository userRepository) {
        this.refreshJwtService = refreshJwtService;
        this.jwtRefreshRepository = jwtRefreshRepository;
        this.userRepository = userRepository;
    }

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(token.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }

    @Override public String generateToken(String refreshToken) {
        String email = refreshJwtService.getEmail(refreshToken.trim());
        Date date = jwtRefreshRepository.getJwtRefreshTokenByToken(refreshToken).getDateExpired();
        if (date.getTime() < new Date().getTime()) {
            throw new TokenExpiredException();
        }
        if (!userRepository.existsByEmail(email)) {
            return "No User";
        }
        User user = userRepository.getUserByEmail(email);
        Date createdDate = new Date();
        Date expiredDate = new Date(createdDate.getTime() + 1000 * 60 * 5);
        return Jwts.builder()
                .subject(user.getEmail())
                .signWith(getKey())
                .expiration(expiredDate)
                .issuedAt(createdDate).compact();
    }

    @Override public ResultCode<String, RuntimeException> getEmail(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(getKey()).build();
            Claims claims = parser.parseSignedClaims(token).getPayload();

            return ResultCode.resultOk(claims.getSubject());
        } catch (JwtException e) {
            return ResultCode.resultError(e);
        }

    }

    @Override public ResultCode<User, String> getUser(String token) {
        ResultCode<String, RuntimeException> result = getEmail(token);
        if (result.isOk()) {
            return ResultCode.resultOk(userRepository.getUserByEmail(result.o()));
        }
        return ResultCode.resultError("Wrong token");
    }
}
