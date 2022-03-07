package com.example.seraphine.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.seraphine.model.ForgotPasswordToken;
import com.example.seraphine.repository.ForgotPasswordTokenRepo;

/**
 * Implementation of ForgotPasswordService.
 * @author Loc Bui Nhien
 */
@Service
@AllArgsConstructor
public class ForgotPasswordTokenServiceImpl implements ForgotPasswordTokenService{
    @Autowired
    private final ForgotPasswordTokenRepo forgotPasswordTokenRepository;

    /**
     * Save forgot password token.
     * @param token
     */
    public void saveForgotPasswordToken(ForgotPasswordToken token) {
        forgotPasswordTokenRepository.save(token);
    }

    /**
     * Get forgot password token by token.
     * @param token
     * @return
     */
    public Optional<ForgotPasswordToken> getToken(String token) {
        return forgotPasswordTokenRepository.findByToken(token);
    }

    /**
     * Set confirmedAt for forgot password token.
     * @param token
     * @return
     */
    public int setConfirmedAt(String token) {
        return forgotPasswordTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}