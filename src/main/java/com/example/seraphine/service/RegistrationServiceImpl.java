package com.example.seraphine.service;

import com.example.seraphine.model.RegistrationRequest;
import com.example.seraphine.model.User;
import com.example.seraphine.model.EmailValidator;
import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.model.EmailSender;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
    private final UserService userService;
    @Autowired
    private final EmailValidator emailValidator;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender senderService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUserName(),
                        request.getPassword(),
                        request.getDateOfBirth(),
                        request.getInsuranceType(),
                        request.getInsuranceName()
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        senderService.sendEmail(request.getEmail(), "[SERAPHINE] Confirm your email now to get started",
                                            "Click on this link to confirm your email and activate your account: " +
                                                    link);

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}