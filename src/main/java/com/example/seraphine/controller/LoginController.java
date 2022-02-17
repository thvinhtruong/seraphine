package com.example.seraphine.controller;

import com.example.seraphine.model.User;
import com.example.seraphine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("user/login")
    public String login(@RequestBody User user) {
        return this.userService.loginUser(user);
    }

}
