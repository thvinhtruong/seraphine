package com.example.seraphine.service;

import com.example.seraphine.model.AccessToken;
import com.example.seraphine.repository.AccessTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private final UserService userService;

    @Autowired
    private final AccessTokenRepo accessTokenRepo;

    public LoginServiceImpl(UserService userService, AccessTokenRepo accessTokenRepo) {
        this.userService = userService;
        this.accessTokenRepo = accessTokenRepo;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        Optional<AccessToken> accessToken_obj = this.accessTokenRepo.findByToken(token);
        if (accessToken_obj.isEmpty()) {
            throw new IllegalArgumentException("token not found");
        }
        AccessToken accessToken = accessToken_obj.get();

        if (accessToken.getAppUser().getPassword().equals(loginRequest.getPassword())) {
            return token;
        } else {
            throw new IllegalStateException("WRONG PASSWORD");
        }
    }
}
