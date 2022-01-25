package com.example.seraphine.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ForgotPasswordRequest {
    private final String email;
    private final String password;
    private final String REpassword;
}