package com.irq3.quizApp.admin.servicesImpl;

import com.irq3.quizApp.admin.dto.requests.InteractionQuizRequest;
import com.irq3.quizApp.admin.dto.requests.InteractionRequest;
import com.irq3.quizApp.admin.dto.response.BanUserResponse;
import com.irq3.quizApp.admin.dto.response.GiveAdminUserResponse;
import com.irq3.quizApp.admin.dto.response.RestrictQuizResponse;
import com.irq3.quizApp.admin.enums.InteractionType;
import com.irq3.quizApp.admin.models.Interaction;
import com.irq3.quizApp.admin.repositories.InteractionRepository;
import com.irq3.quizApp.admin.services.AdminService;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.core.repositories.QuizRepository;
import com.irq3.quizApp.utils.AdminGetter;
import com.irq3.quizApp.utils.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Primary
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final InteractionRepository interactionRepository;

    @Override public ResultCode<BanUserResponse, String> banUser(InteractionRequest interactionRequest) {
        var user = userRepository.getUserById(interactionRequest.getUserId());
        var admin = AdminGetter.getAdmin();
        var ban = Interaction.builder().adminId(admin.getId())
                .interactionDate(LocalDateTime.now())
                .reason(interactionRequest.getReason())
                .userId(interactionRequest.getUserId())
                .interactionType(InteractionType.BAN)
                .build();
        userRepository.delete(user);
        interactionRepository.save(ban);

        return ResultCode.resultOk(BanUserResponse.builder()
                .admin_id(admin.getId())
                .user_id(user.getId())
                .id(ban.getId())
                .reason(interactionRequest.getReason())
                .build());
    }

    @Override public ResultCode<?, String> restrictQuiz(InteractionQuizRequest interactionRequest) {
        var admin = AdminGetter.getAdmin();
        var quiz = quizRepository.getQuizById(interactionRequest.getQuizId());
        if(!quiz.isRestricted()){
            quiz.setRestricted(true);
        }
        var restriction = Interaction.builder().adminId(admin.getId())
                .userId(quiz.getId())
                .reason(interactionRequest.getReason())
                .interactionDate(LocalDateTime.now())
                .interactionType(InteractionType.RESTRICT_QUIZ)
                .build();
        interactionRepository.save(restriction);

        return ResultCode.resultOk(RestrictQuizResponse.builder()
                .id(restriction.getId())
                .quiz_id(quiz.getId())
                .quiz_id(admin.getId())
                .reason(interactionRequest.getReason())
                .build());
    }

    @Override public ResultCode<?, String> giveAdmin(InteractionRequest interactionRequest) {
        var user = userRepository.getUserById(interactionRequest.getUserId());
        var admin = AdminGetter.getAdmin();
        var ban = Interaction.builder().adminId(admin.getId())
                .interactionDate(LocalDateTime.now())
                .reason(interactionRequest.getReason())
                .userId(interactionRequest.getUserId())
                .interactionType(InteractionType.GIVEADMIN)
                .build();
        interactionRepository.save(ban);

        return ResultCode.resultOk(GiveAdminUserResponse.builder()
                .admin_id(admin.getId())
                .user_id(user.getId())
                .id(ban.getId())
                .reason(interactionRequest.getReason())
                .build());
    }
}
