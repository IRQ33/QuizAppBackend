package com.irq3.quizApp.auth.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irq3.quizApp.auth.dto.requests.UserCreate;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmail;
import com.irq3.quizApp.auth.dto.requests.UserLoginName;
import com.irq3.quizApp.auth.dto.response.UserInfo;
import com.irq3.quizApp.auth.dto.response.UserLogin;
import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.enums.Tokens;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.JwtService;
import com.irq3.quizApp.auth.services.RefreshJwtService;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.auth.utils.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshJwtService refreshJwtService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    private final Validator validator;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtRefreshRepository jwtRefreshRepository, RefreshJwtService refreshJwtService, JwtService jwtService, ObjectMapper objectMapper, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshJwtService = refreshJwtService;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        this.validator = validator;
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

        String token = refreshJwtService.generateToken(user);
        UserLogin userLogin = new UserLogin(user,token, Tokens.REFRESH);
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

        String token = refreshJwtService.generateToken(user);
        UserLogin userLogin = new UserLogin(user,token,Tokens.REFRESH);
        return Result.resultOk(userLogin);
    }

    @Override public Result<UserLogin, String> getRefreshToken(Map<String, Object> rawBody) {
        try {
            if(rawBody.containsKey("email")){
                UserLoginEmail userLoginEmail = objectMapper.convertValue(rawBody,UserLoginEmail.class);
                Set<ConstraintViolation<UserLoginEmail>> violations = validator.validate(userLoginEmail);
                if (!violations.isEmpty()) {
                    return Result.resultError(violations.stream().map(ConstraintViolation::getMessage).toList().toString());
                }

                return this.getRefreshToken(userLoginEmail);
            }else if(rawBody.containsKey("username")){
                UserLoginName nameLogin = objectMapper.convertValue(rawBody, UserLoginName.class);

                Set<ConstraintViolation<UserLoginName>> violations = validator.validate(nameLogin);
                if (!violations.isEmpty()) {
                    return Result.resultError(violations.stream().map(ConstraintViolation::getMessage).toList().toString());
                }

                return this.getRefreshToken(nameLogin);
            }
            else {
                return Result.resultError("Bad form of data");
            }
        } catch (RuntimeException e) {
            return Result.resultError("Bad form of data");
        }
    }

    //TODO: Not tested rn
    @Override public Result<UserLogin, String> getAccesToken(String refreshToken) {
        String token = jwtService.generateToken(refreshToken);
        com.irq3.quizApp.auth.utils.Result<User,String> result = jwtService.getUser(token);
        if(result.isOk()){
            return Result.resultOk(new UserLogin(result.o(),token,Tokens.ACCESS));
        }

        return Result.resultError(result.e());
    }


}
