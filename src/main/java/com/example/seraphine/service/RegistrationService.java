package com.example.seraphine.service;


public interface RegistrationService {
    String register(RegistrationRequest request);
    String confirmToken(String token);
}