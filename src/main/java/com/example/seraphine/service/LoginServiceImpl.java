package com.example.seraphine.service;

import com.example.seraphine.model.AccessToken;
import com.example.seraphine.repository.AccessTokenRepo;
import com.example.seraphine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private final UserRepo appUserRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final AccessTokenRepo accessTokenRepo;
    @Autowired
    private final AccessTokenService accessTokenService;

    public LoginServiceImpl(UserRepo appUserRepository, UserService userService, AccessTokenRepo accessTokenRepo, AccessTokenService accessTokenService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
        this.accessTokenRepo = accessTokenRepo;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return token;
    }
}
