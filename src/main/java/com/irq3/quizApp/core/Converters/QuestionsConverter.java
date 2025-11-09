package com.irq3.quizApp.core.Converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irq3.quizApp.core.models.Question;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class QuestionsConverter implements AttributeConverter<List<Question>,String> {
    @Override public String convertToDatabaseColumn(List<Question> questions) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(questions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Bad type of content");
        }
    }

    @Override public List<Question> convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    s,
                    mapper.getTypeFactory().constructCollectionType(List.class, Question.class)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
