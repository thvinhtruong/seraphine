package com.example.seraphine.service;

import com.example.seraphine.controller.EmailValidator;
import com.example.seraphine.model.EmailSender;
import com.example.seraphine.model.ForgotPasswordToken;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ForgotPasswordService {

    private final UserService appUserService;
    private final EmailValidator emailValidator;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    @Autowired
	private EmailSender senderService;

    public String forgot_password(ForgotPasswordRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        if (request.getPassword()!=request.getREpassword()){
            throw new IllegalStateException("Re-enter password doesn't match");
        }

        
        String token = appUserService.Forgot(
                request.getEmail(),request.getPassword());

        String link = "http://localhost:8080/api/v1/forgot_password/reset_password?token=" + token;

        senderService.sendEmail(request.getEmail(), "[SERAPHINE] Reset your password", "Click on this link to reset password for your account: "+link);

        return token;
    }


    @Transactional
    public String forgotPasswordToken(String token) {
        ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (forgotPasswordToken.getConfirmedAt() != null) {
            throw new IllegalStateException("password already changed");
        }

        LocalDateTime expiredAt = forgotPasswordToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        forgotPasswordTokenService.setConfirmedAt(token);
        appUserService.updatePassword(forgotPasswordToken.getAppUser(),forgotPasswordToken.getPassword());
        return "confirmed";
    }
}