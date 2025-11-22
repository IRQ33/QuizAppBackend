package com.irq3.quizApp.core.servicesImpl;

import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.core.dto.request.CreateQuizRequest;
import com.irq3.quizApp.core.dto.response.QuizInfoResponse;
import com.irq3.quizApp.core.dto.response.QuizMadeResponse;
import com.irq3.quizApp.core.mappers.QuizMapper;
import com.irq3.quizApp.core.models.Quiz;
import com.irq3.quizApp.core.repositories.QuizRepository;
import com.irq3.quizApp.core.services.QuizService;
import com.irq3.quizApp.utils.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;


    @Override public ResultCode<QuizMadeResponse, String> createQuiz(CreateQuizRequest createQuizRequest) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) return ResultCode.resultError("No user");
        Quiz quiz = Quiz.builder().name(createQuizRequest.getName()).hiddenName(createQuizRequest.getName())
                .content(createQuizRequest.getContent()).description(createQuizRequest.getDescription())
                .createdAt(LocalDateTime.now()).user_id(user.getId()).build();
        quizRepository.save(quiz);
        return ResultCode.resultOk(quizMapper.toQuizMadeResponse(quiz));
    }

    @Override public ResultCode<String, String> removeQuiz(long id) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var quiz = quizRepository.getQuizById(id);
        if (quiz == null) {
            return ResultCode.resultBadRequest("There is no quiz with that id");
        }
        if (user.getId() != quiz.getId()) {
            return ResultCode.resultBadRequest("No permissions");
        }

        quizRepository.delete(quiz);
        return ResultCode.resultOk("We made it");
    }

    @Override public ResultCode<QuizInfoResponse, String> getQuiz(long id) {
        var quiz = quizRepository.getQuizById(id);
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ((user == null || user.isMinor()) && quiz.isRestricted()) {
            return ResultCode.resultBadRequest("This quiz is restricted, so you have to be logged in to learn it");
        }
        if (quiz == null) {
            return ResultCode.resultBadRequest("There is no quiz with that id");
        }
        return ResultCode.resultOk(quizMapper.toQuizInfoResponse(quiz));
    }

    @Override public ResultCode<QuizInfoResponse, String> changeQuiz(Quiz quiz) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (quiz == null) {
            return ResultCode.resultBadRequest("There is no quiz with that id");
        }
        if (user.getId() != quiz.getId()) {
            return ResultCode.resultBadRequest("No permissions");
        }

        quiz.changMe(quiz);
        return ResultCode.resultOk(quizMapper.toQuizInfoResponse(quiz));
    }

    @Override public ResultCode<List<QuizInfoResponse>, String> getAllQuizzes() {
        return ResultCode.resultOk(quizRepository.findAll()
                .stream().map(quizMapper::toQuizInfoResponse).toList());
    }

    @Override public ResultCode<List<QuizMadeResponse>, String> searchQuizzes(String name) {
        return ResultCode.resultOk(quizRepository.getSimilarQuizzes(name)
                .stream().map(quizMapper::toQuizMadeResponse).toList());
    }

}
