package com.irq3.quizApp.auth.startup;

import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
class AdminCreator implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Override public void run(String... args) throws Exception {
        User user = User.builder().userName("Admin123")
                .email("sigma@gmail.com")
                .password(passwordEncoder.encode("haslo123"))
                .permissions(List.of(Permissions.USER, Permissions.ADMIN, Permissions.OWNER))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .dateOfBirth(LocalDate.of(2006, 1, 22).atStartOfDay())
                .build();

        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }

    }
}
