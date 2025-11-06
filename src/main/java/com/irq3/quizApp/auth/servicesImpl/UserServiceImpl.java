package com.irq3.quizApp.auth.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irq3.quizApp.auth.dto.requests.UserCreateRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginEmailRequest;
import com.irq3.quizApp.auth.dto.requests.UserLoginNameRequest;
import com.irq3.quizApp.auth.dto.response.UserInfoResponse;
import com.irq3.quizApp.auth.dto.response.UserLoginResponse;
import com.irq3.quizApp.auth.enums.Permissions;
import com.irq3.quizApp.auth.enums.Tokens;
import com.irq3.quizApp.auth.exceptions.TokenExpiredException;
import com.irq3.quizApp.auth.models.User;
import com.irq3.quizApp.auth.repositories.JwtRefreshRepository;
import com.irq3.quizApp.auth.repositories.UserRepository;
import com.irq3.quizApp.auth.services.JwtService;
import com.irq3.quizApp.auth.services.RefreshJwtService;
import com.irq3.quizApp.auth.services.UserService;
import com.irq3.quizApp.utils.Result;
import com.irq3.quizApp.utils.ResultCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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

    @Override public Result<UserInfoResponse,String> createUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .userName(userCreateRequest.getName().trim())
                .email(userCreateRequest.getEmail().trim().toLowerCase(Locale.ROOT))
                .password(passwordEncoder.encode(userCreateRequest.getPassword().trim()))
                .permissions(List.of(Permissions.USER))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        return Result.resultOk(new UserInfoResponse(user));
    }

    @Override public ResultCode<String, String> deleteUser(long id) {
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(id==user.getId()){
            userRepository.delete(user);
            return ResultCode.resultOk("Deleted user: "+user.getUserName());
        }
        return ResultCode.resultBadRequest("No authorities");
    }

    @Override public UserInfoResponse getUser(long id) {
        return new UserInfoResponse(userRepository.getUserById(id));
    }

    @Override public long countOfUsers() {
        return userRepository.count();
    }

    @Override public Result<UserLoginResponse, String> getRefreshToken(UserLoginEmailRequest userLoginEmailRequest) {
        if(!userRepository.existsByEmail(userLoginEmailRequest.getEmail())){
            return Result.resultError("No user with this email");
        }
        User user = userRepository.getUserByEmail(userLoginEmailRequest.getEmail());
        if(!passwordEncoder.matches(userLoginEmailRequest.getPassword(), user.getPassword())){
            return Result.resultError("Wrong password");
        }
        //TODO: more checks in the future

        String token = refreshJwtService.generateToken(user);
        UserLoginResponse userLogin = new UserLoginResponse(user,token, Tokens.REFRESH);
        return Result.resultOk(userLogin);
    }

    @Override public Result<UserLoginResponse, String> getRefreshToken(UserLoginNameRequest userLoginNameRequest) {
        if(!userRepository.existsByUserName(userLoginNameRequest.getUserName())){
            return Result.resultError("No user with this email");
        }
        User user = userRepository.getUserByUserName(userLoginNameRequest.getUserName());
        if(!passwordEncoder.matches(userLoginNameRequest.getPassword(), user.getPassword())){
            return Result.resultError("Wrong password");
        }

        //TODO: more checks in the future

        String token = refreshJwtService.generateToken(user);
        UserLoginResponse userLogin = new UserLoginResponse(user,token,Tokens.REFRESH);
        return Result.resultOk(userLogin);
    }

    @Override public Result<UserLoginResponse, String> getRefreshToken(Map<String, Object> rawBody) {
        try {
            if(rawBody.containsKey("email")){
                UserLoginEmailRequest userLoginEmailRequest = objectMapper.convertValue(rawBody, UserLoginEmailRequest.class);
                Set<ConstraintViolation<UserLoginEmailRequest>> violations = validator.validate(userLoginEmailRequest);
                if (!violations.isEmpty()) {
                    return Result.resultError(violations.stream().map(ConstraintViolation::getMessage).toList().toString());
                }

                return this.getRefreshToken(userLoginEmailRequest);
            }else if(rawBody.containsKey("username")){
                UserLoginNameRequest nameLogin = objectMapper.convertValue(rawBody, UserLoginNameRequest.class);

                Set<ConstraintViolation<UserLoginNameRequest>> violations = validator.validate(nameLogin);
                if (!violations.isEmpty()) {
                    return Result.resultError(violations.stream().map(ConstraintViolation::getMessage).toList().toString());
                }

                return this.getRefreshToken(nameLogin);
            }
            else {
                return Result.resultError("Bad form of data");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Result.resultError("Bad form of data");
        }
    }

    @Override public Result<UserLoginResponse, String> getAccesToken(String refreshToken) {
        String token = "";
        try {
            token = jwtService.generateToken(refreshToken);
        }catch (TokenExpiredException e){
            return Result.resultError("Token expired");
        }
        Result<User,String> result = jwtService.getUser(token);
        if(result.isOk()){
            return Result.resultOk(new UserLoginResponse(result.o(),token,Tokens.ACCESS));
        }

        return Result.resultError(result.e());
    }


}
