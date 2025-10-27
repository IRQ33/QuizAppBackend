package com.irq3.quizApp.auth.servicesImpl;

import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmail;
import com.irq3.quizApp.auth.dto.requests.UserLoginName;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.dto.response.UserLogin;
import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.RefreshJwtService;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.auth.utils.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshJwtService jwtService;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtRefreshRepository jwtRefreshRepository, RefreshJwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override public Result<UserInfo,String> createUser(UserCreate userCreate) {
        User user = User.builder()
                .userName(userCreate.getName().trim())
                .email(userCreate.getEmail().trim().toLowerCase(Locale.ROOT))
                .password(passwordEncoder.encode(userCreate.getPassword().trim()))
                .permissions(List.of(Permissions.USER))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepository.save(user);
        return Result.resultOk(new UserInfo(user));
    }

    @Override public long countOfUsers() {
        return userRepository.count();
    }

    @Override public Result<UserLogin, String> getRefreshToken(UserLoginEmail userLoginEmail) {
        if(!userRepository.existsByEmail(userLoginEmail.getEmail())){
            return Result.resultError("No user with this email");
        }
        User user = userRepository.getUserByEmail(userLoginEmail.getEmail());
        if(!passwordEncoder.matches(userLoginEmail.getPassword(), user.getPassword())){
            return Result.resultError("Wrong password");
        }
        //TODO: more checks in the future

        String token = jwtService.generateToken(user);
        UserLogin userLogin = new UserLogin(user,token);
        return Result.resultOk(userLogin);
    }

    @Override public Result<UserLogin, String> getRefreshToken(UserLoginName userLoginName) {
        if(!userRepository.existsByUserName(userLoginName.getUserName())){
            return Result.resultError("No user with this email");
        }
        User user = userRepository.getUserByUserName(userLoginName.getUserName());
        if(!passwordEncoder.matches(userLoginName.getPassword(), user.getPassword())){
            return Result.resultError("Wrong password");
        }

        //TODO: more checks in the future

        String token = jwtService.generateToken(user);
        UserLogin userLogin = new UserLogin(user,token);
        return Result.resultOk(userLogin);
    }
}
