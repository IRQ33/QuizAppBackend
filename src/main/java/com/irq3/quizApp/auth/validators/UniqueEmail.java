package com.irq3.quizApp.auth.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailImpl.class)
public @interface UniqueEmail {
    String message() default "Email have to be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
