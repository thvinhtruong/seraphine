package com.example.seraphine.service;


/**
 * Service interface to access from data and model, also create entity for registration
 * @author Loc Bui Nhien
 */
public interface RegistrationService {
    String register(RegistrationRequest request);
    String confirmToken(String token);
}