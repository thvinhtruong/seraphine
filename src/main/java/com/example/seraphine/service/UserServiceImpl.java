package com.example.seraphine.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Optional;

import com.example.seraphine.model.*;
import com.example.seraphine.repository.AppointmentRepo;
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
    private final AccessTokenService accessTokenService;
    private final ForgotPasswordTokenService forgotPasswordTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";

    @Autowired
    private AppointmentRepo appointmentRepo;
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

    @Override
    public String loginUser(String username, String password) {
        boolean userExists = appUserRepository.findByUsername(username).isPresent();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordExists = encoder.matches(password, appUserRepository.findByUsername(username).get().getPassword());
        if (userExists && passwordExists) {
            String token = UUID.randomUUID().toString();
            AccessToken accessToken = new AccessToken(token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(60),
                    username, password);

            accessTokenService.saveAccessToken(accessToken);
            return token;
        } else {
            throw new IllegalStateException("Wrong user name or password");
        }
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

    @Override
    public void cancelAppointment(Long user_id, Long appointment_id) {
        Optional<User> user_obj = this.appUserRepository.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("User not found");
        }
        User user = user_obj.get();
        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();

        appointment.setUser_id(0L);
        appointment.setStatus(false);

        List<Appointment> user_appointment = user.getMyAppointment();
        for (int i=0; i<user_appointment.size(); i++) {
            if (user_appointment.get(i).getUser_id().equals(user.getId())) {
                user_appointment.remove(i);
            }
        }
    }

    @Override
    public List<Appointment> showAllAppointments(Long user_id) {
        return this.appointmentRepo.findByUserId(user_id);
    }
}
