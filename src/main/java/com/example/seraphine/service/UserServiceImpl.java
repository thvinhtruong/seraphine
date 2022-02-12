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

/**
 * Service for User.
 * @author Vinh Truong Canh Thanh, Loc Bui Nhien, Tri Nguyen Minh
 */
@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepo appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";

    /**
     * Login for user using username.
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                                .orElseThrow(() ->
                                new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    /**
     * Sign up for user.
     * @param appUser
     * @return
     */
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
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }

    /** 
     * Forgot password for user.
     * @param email
     * @param password
    */
    @Override
    public String Forgot(String email, String password)
    {
        boolean userExists = appUserRepository.findByEmail(email).isPresent();

        if (userExists){
            String token  = UUID.randomUUID().toString();
            ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    password);
            forgotPasswordTokenService.saveForgotPasswordToken(forgotPasswordToken);
            
            return token;
        }
        return null;
    }

    /**
     * Enable user.
     * @param email
     */
    @Override
    public void enableAppUser(String email){
        appUserRepository.enableAppUser(email);
    }

    /**
     * Update password for user.
     * @param user
     * @param password
     */
    @Override
    public void updatePassword(User user, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);
    }

    /**
     * Show personal information for user.
     * @param id
     * @return
     */
    @Override
    public User showPersonalInfor(Long id) {
        Optional<User> user = this.appUserRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("User not found");
        }
        return user.get();
    }

    /**
     * Edit personal information for user.
     * @param id Long
     * @param newUser User
     */
    @Override
    public void editPersonalInfor(Long id, User newUser) {
        this.appUserRepository.findById(id).map(user -> {
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
