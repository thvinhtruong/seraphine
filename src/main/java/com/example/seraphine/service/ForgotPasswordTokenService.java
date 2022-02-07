package com.example.seraphine.service;

import com.example.seraphine.model.ForgotPasswordToken;

import java.util.Optional;

public interface ForgotPasswordTokenService {
    void saveForgotPasswordToken(ForgotPasswordToken token);
    Optional<ForgotPasswordToken> getToken(String token);
    int setConfirmedAt(String token);
}
