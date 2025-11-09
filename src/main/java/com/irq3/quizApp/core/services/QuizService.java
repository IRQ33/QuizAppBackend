package com.irq3.quizApp.core.services;

import com.irq3.quizApp.core.dto.request.CreateQuizRequest;
import com.irq3.quizApp.core.dto.response.QuizInfoResponse;
import com.irq3.quizApp.core.dto.response.QuizMadeResponse;
import com.irq3.quizApp.core.models.Quiz;
import com.irq3.quizApp.utils.ResultCode;

import java.util.List;

public interface QuizService {
    ResultCode<QuizMadeResponse,String> createQuiz(CreateQuizRequest quiz);
    ResultCode<String, String> removeQuiz(long id);
    ResultCode<QuizInfoResponse,String> getQuiz(long id);
    ResultCode<QuizInfoResponse,String> changeQuiz(Quiz quiz);
    ResultCode<List<QuizInfoResponse>,String> getAllQuizzes();
    ResultCode<List<QuizMadeResponse>,String> searchQuizzes(String name);
}
