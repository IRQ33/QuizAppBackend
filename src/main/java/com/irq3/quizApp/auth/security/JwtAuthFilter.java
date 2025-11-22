package com.irq3.quizApp.auth.security;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.services.JwtService;
import com.irq3.quizApp.auth.services.RefreshJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final RefreshJwtService refreshJwtService;

    JwtAuthFilter(JwtService jwtService, RefreshJwtService refreshJwtService) {
        this.jwtService = jwtService;
        this.refreshJwtService = refreshJwtService;
    }

    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null) {
            User user = jwtService.getUser(token).o();

            if (user != null) {
                List<SimpleGrantedAuthority> authorities = user.getPermissions().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .toList();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }else {
                response.resetBuffer();
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader("Content-Type", "application/json");
                response.getOutputStream().print("{\"errorMessage\":\"You have invalid access key\"}");
                response.flushBuffer();
                return;
            }

        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/api/v1/user/register") ||
                request.getRequestURI().equals("/api/v1/user/login") ||
                request.getRequestURI().equals("/api/v1/user/access") ||
                request.getRequestURI().equals("/api/v1/quiz/all") ||
                request.getRequestURI().startsWith("/api/v1/quiz/search") ||
                request.getRequestURI().startsWith("/v3/") ||
                request.getRequestURI().startsWith("/swagger-ui") ||
                request.getRequestURI().equals("/swagger-ui.html") ||
                request.getRequestURI().startsWith("/webjars/")||
                request.getRequestURI().equals("/online");
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
