package com.irq3.quizApp.core.mappers;

import com.irq3.quizApp.core.dto.response.QuizInfoResponse;
import com.irq3.quizApp.core.dto.response.QuizMadeResponse;
import com.irq3.quizApp.core.models.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuizInfoResponse toQuizInfoResponse(Quiz quiz);

    QuizMadeResponse toQuizMadeResponse(Quiz quiz);
}
