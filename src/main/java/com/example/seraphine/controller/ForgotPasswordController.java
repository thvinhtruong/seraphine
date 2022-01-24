package com.example.seraphine.controller;

import lombok.AllArgsConstructor;

import com.example.seraphine.service.ForgotPasswordRequest;
import com.example.seraphine.service.ForgotPasswordService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/forgot_password")
@AllArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping
    public String forgot_password(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.forgot_password(request);
    }

    @GetMapping(path = "reset_password")
    public String confirm(@RequestParam("token") String token) {
        return forgotPasswordService.forgotPasswordToken(token);
    }

}