package com.example.seraphine.service;

import com.example.seraphine.model.User;
import com.example.seraphine.controller.EmailValidator;
import com.example.seraphine.model.ConfirmationToken;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ForgotPasswordService {

    private final UserService appUserService;
    private final EmailValidator emailValidator;

    public String Forgot(ForgotPasswordRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return null;
    }
}