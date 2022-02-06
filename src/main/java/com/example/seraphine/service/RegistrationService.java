package com.example.seraphine.service;

import com.example.seraphine.model.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest request);
    String confirmToken(String token);
}
