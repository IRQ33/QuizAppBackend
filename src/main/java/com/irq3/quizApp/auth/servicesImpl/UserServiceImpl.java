package com.irq3.quizApp.auth.servicesImpl;

import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.auth.utils.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override public Result<UserInfo,String> createUser(UserCreate userCreate) {
        //TODO: check make checking for it
        User user = User.builder()
                .userName(userCreate.getName())
                .email(userCreate.getEmail())
                .password(passwordEncoder.encode(userCreate.getPassword()))
                .permissions(List.of(Permissions.USER))
                .build();
        userRepository.save(user);
        return Result.resultOk(new UserInfo(user));
    }

    @Override public long countOfUsers() {
        return userRepository.count();
    }


}
