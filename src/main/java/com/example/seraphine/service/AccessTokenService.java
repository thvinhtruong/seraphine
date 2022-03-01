package com.example.seraphine.service;

import com.example.seraphine.model.AccessToken;
import java.util.Optional;

public interface AccessTokenService {
    void saveAccessToken(AccessToken token);
    Optional<AccessToken> getToken(String token);
    int setConfirmedAt(String token);
}
