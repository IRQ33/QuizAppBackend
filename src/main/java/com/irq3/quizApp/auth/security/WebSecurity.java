package com.irq3.quizApp.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class WebSecurity {
    //TODO: Auth2 there
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req->{
            req.requestMatchers("/api/v1/register","/api/v1/login","/api/v1/count","getUsers").permitAll().anyRequest().authenticated();
        }).build();
    }

    @Bean PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
