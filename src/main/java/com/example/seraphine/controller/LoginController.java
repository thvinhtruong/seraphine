package com.example.seraphine.controller;

import com.example.seraphine.service.LoginRequest;
import com.example.seraphine.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/signin")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        return this.loginService.login(loginRequest);
    }
}
