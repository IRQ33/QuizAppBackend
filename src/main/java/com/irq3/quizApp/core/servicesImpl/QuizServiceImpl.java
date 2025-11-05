package com.irq3.quizApp.core.servicesImpl;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.core.dto.request.CreateQuiz;
import com.irq3.quizApp.core.dto.response.QuizMade;
import com.irq3.quizApp.core.models.Quiz;
import com.irq3.quizApp.core.repositories.QuizRepository;
import com.irq3.quizApp.core.services.QuizService;
import com.irq3.quizApp.utils.ResultCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override public ResultCode<QuizMade, String> createQuiz(CreateQuiz createQuiz) {
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user==null) return ResultCode.resultError("No user");
        Quiz quiz = Quiz.builder().name(createQuiz.getName()).hiddenName(createQuiz.getName())
                .content(createQuiz.getContent()).description(createQuiz.getDescription())
                .createdAt(new Date()).user_id(user.getId()).build();
        quizRepository.save(quiz);
        return ResultCode.resultOk(new QuizMade(quiz));
    }
}
