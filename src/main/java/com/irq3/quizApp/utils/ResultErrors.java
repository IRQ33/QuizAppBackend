package com.irq3.quizApp.utils;


import com.fasterxml.jackson.annotation.JsonInclude;

//it can be useful in the future
@JsonInclude(JsonInclude.Include.NON_NULL)
record ResultErrors <O, E, E1>(O o, E e, E1 e1){

    public static <O, E, E1> ResultErrors<O, E, E1> resultOk(O value) {
        return new ResultErrors<>(value, null,null);
    }

    public static <O, E, E1> ResultErrors<O, E, E1> resultError(E value) {
        return new ResultErrors<>(null, value,null);
    }
    public static <O, E, E1> ResultErrors<O, E, E1> resultError2(E1 value) {
        return new ResultErrors<>(null, null,value);
    }
}
