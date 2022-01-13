package com.example.seraphine.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
    
    private final UserRepo appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
        .orElseThrow(() -> 
        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG)));
    }

    public String signUpUser(User appUser)
    {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token  = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }
    
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
