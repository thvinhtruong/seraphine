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
<<<<<<< HEAD
@AllArgsConstructor
public class UserService implements UserDetailsService{
    
    @Autowired
    private final UserRepo userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
        .orElseThrow(() -> 
        new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    public String signUpUser(User appUser)
    {
        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);

        String token  = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        
        return token;
    }

    public String Forgot(String email, String password)
    {
        boolean userExists = userRepository.findByEmail(email).isPresent();

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
    
    public int enableAppUser(String email){
        return userRepository.enableAppUser(email);
    }

    public void updatePassword(User user, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(password);

        user.setPassword(encodePassword);
    }
=======
public interface UserService extends UserDetailsService {
    String signUpUser(User appUser);
    String Forgot(String email, String password);
    void enableAppUser(String email);
    void updatePassword(User user, String password);
    User showPersonalInfor(Long id);
    User editPersonalInfor(Long id, User newUser);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
>>>>>>> refs/remotes/origin/main
}
