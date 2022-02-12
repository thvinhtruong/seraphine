package com.example.seraphine.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.repository.ConfirmationTokenRepo;

/**
 * Implementation of ConfirmationTokenService.
 * @author Loc Bui Nhien
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
    @Autowired
    private final ConfirmationTokenRepo confirmationTokenRepository;

    /**
     * Save confirmation token.
     * @param token ConfirmationToken
     */
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    /**
     * Get confirmation token by token.
     * @param token String
     * @return Optional
     */
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    /**
     * Set confirmedAt for confirmation token.
     * @param token String
     * @return int
     */
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}