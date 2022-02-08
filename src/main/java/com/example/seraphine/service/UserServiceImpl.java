package com.example.seraphine.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.model.ForgotPasswordToken;
import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    
    @Autowired
    private final UserRepo appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
        .orElseThrow(() -> 
        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG)));
    }

    @Override
    public String signUpUser(User appUser)
    {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        boolean usernameExists = appUserRepository.findByUsername(appUser.getUsername()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already taken");
        }

        if (usernameExists){
            throw new IllegalStateException("username already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token  = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }

    @Override
    public String Forgot(String email, String password)
    {
        boolean userExists = appUserRepository.findByEmail(email).isPresent();

        if (userExists){
            String token  = UUID.randomUUID().toString();
            ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),password);
            forgotPasswordTokenService.saveForgotPasswordToken(forgotPasswordToken);
            
            return token;
        }
        return null;
    }

    @Override
    public void enableAppUser(String email){
        appUserRepository.enableAppUser(email);
    }

    @Override
    public void updatePassword(User user, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);
    }

    @Override
    public User showPersonalInfor(Long id) {
        Optional<User> user = this.appUserRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("User not found");
        }

        // can we use string with the json format? because maybe we dont want user to see something.
        return user.get();
    }

    @Override
    public User editPersonalInfor(Long id, User newUser) {
        return (User) this.appUserRepository.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setInsuranceName(newUser.getInsuranceName());
            user.setInsuranceType(newUser.getInsuranceType());
            return null;
        }).orElseGet(()->{
            newUser.setId(id);
            return this.appUserRepository.save(newUser);
        });
    }
}
