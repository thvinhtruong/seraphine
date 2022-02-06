package com.example.seraphine.service;

import org.springframework.stereotype.Service;
import com.example.seraphine.model.User;

@Service
public interface UserService {
    String signUpUser(User appUser);
    String Forgot(String email, String password);
    void enableAppUser(String email);
    void updatePassword(User user, String password);
    User showPersonalInfor(Long id);
    User editPersonalInfor(Long id, User newUser);
    
}
