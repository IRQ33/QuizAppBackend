package com.irq3.quizApp.auth.validators;

import com.irq3.quizApp.auth.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailFree implements ConstraintValidator<EmailAvailable, String> {
    @Autowired UserRepository userRepository;

    @Override public void initialize(EmailAvailable constraintAnnotation) {

    }

    @Override public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return !userRepository.existsByEmail(s);

    }
}
