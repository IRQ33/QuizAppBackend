package com.irq3.quizApp.auth;

import com.irq3.quizApp.core.Converters.QuestionsConverter;
import com.irq3.quizApp.core.models.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

class QuestionConverters {

    @Test
    void testThem(){
        QuestionsConverter q = new QuestionsConverter();

        var test = List.of(new Question("test","test"),new Question("test2","test2"));
        var string = q.convertToDatabaseColumn(test);
        System.out.println(string);
        List<Question> questions = q.convertToEntityAttribute(string);
        assert test.equals(questions);

    }
}
