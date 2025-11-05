package com.irq3.quizApp.core.services;

import com.irq3.quizApp.core.dto.request.CreateQuiz;
import com.irq3.quizApp.core.dto.response.QuizMade;
import com.irq3.quizApp.utils.ResultCode;

public interface QuizService {
    ResultCode<QuizMade,String> createQuiz(CreateQuiz quiz);

}
