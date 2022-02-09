package com.example.seraphine.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request object to send to user to reset password
 * @author Loc Bui Nhien
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ForgotPasswordRequest {
    private final String email;
    private final String password;
    private final String REpassword;
}