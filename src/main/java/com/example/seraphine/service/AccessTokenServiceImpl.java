package com.example.seraphine.service;

import com.example.seraphine.model.AccessToken;
import com.example.seraphine.repository.AccessTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {
    @Autowired
    private final AccessTokenRepo accessTokenRepo;

    @Override
    public void saveAccessToken(AccessToken token) {
        this.accessTokenRepo.save(token);
    }

    @Override
    public Optional<AccessToken> getToken(String token) {
        return this.accessTokenRepo.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return this.accessTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }
}