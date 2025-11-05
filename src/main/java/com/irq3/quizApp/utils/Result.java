package com.irq3.quizApp.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Result<O, E>(O o, E e) {
    public static <O, E> Result<O, E> resultOk(O value) {
        return new Result<>(value, null);
    }

    public static <O, E> Result<O, E> resultError(E value) {
        return new Result<>(null, value);
    }
    // if no e(error) everything works fine
    public boolean isOk(){
        return e==null;
    }
}
