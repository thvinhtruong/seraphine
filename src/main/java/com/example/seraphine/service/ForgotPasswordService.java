package com.example.seraphine.service;

public interface ForgotPasswordService {
    String forgot_password(ForgotPasswordRequest request);
    String forgotPasswordToken(String token);
}
