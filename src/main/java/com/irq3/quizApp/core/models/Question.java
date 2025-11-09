package com.irq3.quizApp.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Question {
    @JsonProperty("question")
    String question;
    @JsonProperty("response")
    String response;

}
