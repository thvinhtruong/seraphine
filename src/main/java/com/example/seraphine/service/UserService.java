package com.example.seraphine.service;

import com.example.seraphine.model.ConfirmationToken;
import com.example.seraphine.model.ForgotPasswordToken;
import com.example.seraphine.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.seraphine.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for User, which includes authentication, registration, and reset password.
 * @author Loc Bui Nhien
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
    @Autowired
    private final UserRepo appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
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

    // Nhien Loc, please review this!!!
    public void enableAppUser(String email){
        appUserRepository.enableAppUser(email);
    }

    public void updatePassword(User user, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);
    }

    public User showPersonalInfor(Long id) {
        Optional<User> user = this.appUserRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("User not found");
        }

        // can we use string with the json format? because maybe we dont want user to see something.
        return user.get();
    }

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
