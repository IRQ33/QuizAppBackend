package com.irq3.quizApp.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)

public record ResultCode<O,E>(O o, E e, @JsonIgnore HttpStatus status) {
    public static <O, E> ResultCode<O, E> resultOk(O value) {
        return new ResultCode<>(value, null,HttpStatus.OK);
    }
    public static <O, E> ResultCode<O, E> resultBadRequest(O value) {
        return new ResultCode<>(value, null, HttpStatus.BAD_REQUEST);
    }
    public static <O, E> ResultCode<O, E> resultError(E value) {
        return new ResultCode<>(null, value, HttpStatus.BAD_REQUEST);
    }
    public static <O, E> ResultCode<O, E> resultCustom(O value, HttpStatus status) {
        return new ResultCode<>(value, null, status);
    }

}
