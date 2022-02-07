package com.example.seraphine.controller;

import lombok.AllArgsConstructor;

import com.example.seraphine.service.ForgotPasswordRequest;
import com.example.seraphine.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for the forgot password service.
 * <p>
 * @author Loc Bui Nhien
 */
@RestController
@RequestMapping(path = "api/v1/forgot_password")
@AllArgsConstructor
public class ForgotPasswordController {
    @Autowired
    private final ForgotPasswordService forgotPasswordService;


    /**
     * Handle the forgot password request.
     * @param request
     * @return
     */
    @PostMapping
    public String forgot_password(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.forgot_password(request);
    }

    /**
     * Handle the reset password link.
     * @param token
     */
    @GetMapping(path = "reset_password")
    public String confirm(@RequestParam("token") String token) {
        return forgotPasswordService.forgotPasswordToken(token);
    }

}