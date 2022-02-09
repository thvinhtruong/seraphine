package com.example.seraphine.service;

import com.example.seraphine.model.ConfirmationToken;

import java.util.Optional;

/**
 * Service interface to access from data and model, also create entity for confirmation token
 * @author Loc Bui Nhien
 */
public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}

