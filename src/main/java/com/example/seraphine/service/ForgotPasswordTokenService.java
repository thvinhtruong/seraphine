package com.example.seraphine.service;

import com.example.seraphine.model.ForgotPasswordToken;

import java.util.Optional;

/**
 * Service interface to access from data and model, also create entity for forgot password token
 * @author Loc Bui Nhien
 */
public interface ForgotPasswordTokenService {
    void saveForgotPasswordToken(ForgotPasswordToken token);
    Optional<ForgotPasswordToken> getToken(String token);
    int setConfirmedAt(String token);
}
