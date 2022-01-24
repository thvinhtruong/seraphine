package com.example.seraphine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.seraphine.model.ForgotPasswordToken;
import com.example.seraphine.repository.ForgotPasswordTokenRepo;

@Service
@AllArgsConstructor
public class ForgotPasswordTokenService {

    private final ForgotPasswordTokenRepo confirmationTokenRepository;

    public void saveForgotPasswordToken(ForgotPasswordToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ForgotPasswordToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}