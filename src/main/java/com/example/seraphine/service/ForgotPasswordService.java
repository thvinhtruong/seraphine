package com.example.seraphine.service;

/**
 * Interface to access from data and model, also create entity for forgot password token
 * @author Loc Bui Nhien
*/
public interface ForgotPasswordService {
    String forgot_password(ForgotPasswordRequest request);
    String forgotPasswordToken(String token);
}
