package com.example.seraphine.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.seraphine.model.User;

/**
 * Service for User, which includes authentication, registration, and reset password.
 * @author Loc Bui Nhien
 */
@Service
public interface UserService extends UserDetailsService {
    String signUpUser(User appUser);
    String Forgot(String email, String password);
    void enableAppUser(String email);
    void updatePassword(User user, String password);
    User showPersonalInfor(Long id);
    User editPersonalInfor(Long id, User newUser);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
