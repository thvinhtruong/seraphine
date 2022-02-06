package com.example.seraphine.service;

import com.example.seraphine.model.ConfirmationToken;

import java.util.Optional;

<<<<<<< HEAD
public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}
=======
import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.repository.ConfirmationTokenRepo;

/**
 * Service for ConfirmationToken.
 * @author Loc Bui Nhien
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
>>>>>>> refs/remotes/origin/main
