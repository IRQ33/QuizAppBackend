package com.irq3.quizApp.auth.exceptions;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token expired");
    }
}
