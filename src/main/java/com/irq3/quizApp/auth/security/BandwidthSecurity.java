package com.irq3.quizApp.auth.security;

import com.irq3.quizApp.auth.controllers.UserController;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
class BandwidthSecurity implements HandlerInterceptor {

    //24 / hour
    private final Bucket bucket = Bucket.builder().addLimit(limit -> limit.capacity(5).refillGreedy(5, Duration.ofHours(1))).build();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getBeanType().equals(UserController.class)) {
                if (!bucket.tryConsume(1)) {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    return false;
                }
            }
        }

        return true;
    }
}
