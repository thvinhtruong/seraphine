package com.example.seraphine.service;

import com.example.seraphine.model.ForgotPasswordRequest;

public interface ForgotPasswordService {
    String forgot_password(ForgotPasswordRequest request);
    String forgotPasswordToken(String token);
}
