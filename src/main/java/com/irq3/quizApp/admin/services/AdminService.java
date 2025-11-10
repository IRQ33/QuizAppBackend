package com.irq3.quizApp.admin.services;

import com.irq3.quizApp.admin.dto.requests.InteractionQuizRequest;
import com.irq3.quizApp.admin.dto.requests.InteractionRequest;
import com.irq3.quizApp.admin.dto.response.BanUserResponse;
import com.irq3.quizApp.utils.ResultCode;

public interface AdminService {
    ResultCode<BanUserResponse, String> banUser(InteractionRequest interactionRequest);
    ResultCode<?, String> restrictQuiz(InteractionQuizRequest interactionRequest);
    ResultCode<?, String> giveAdmin(InteractionRequest interactionRequest);
}
