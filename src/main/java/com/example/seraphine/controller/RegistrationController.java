package com.example.seraphine.controller;

import lombok.AllArgsConstructor;
import com.example.seraphine.service.RegistrationRequest;
import com.example.seraphine.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for the registration service.
 * <p>
 * @author Loc Bui Nhien
 */
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private final RegistrationService registrationService;

    /**
     * Create a new account, given the data provided.
     * <p>
     * @param request Registration request
     * @return newly created account
     */
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    /**
     * Handle the confirmation link.
     *
     * @param token for the new user
     */
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}