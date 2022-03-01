package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.seraphine.model.User;

import java.util.List;

/**
 * Service for User.
 * @author Vinh Truong Canh Thanh, Loc Bui Nhien
*/
@Service
public interface UserService extends UserDetailsService {
    String signUpUser(User appUser);
    String loginUser(String username, String password);
    String Forgot(String email, String password);
    void enableAppUser(String email);
    void updatePassword(User user, String password);
    User showPersonalInfor(Long id);
    void editPersonalInfor(Long id, User newUser);
    void cancelAppointment(Long user_id, Long appointment_id);
    List<Appointment> showAllAppointments(Long user_id);

    /**
     * Load user by username
     * @param username String
     * @throws UsernameNotFoundException
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
