package com.example.seraphine.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.seraphine.model.User;
/** 
 * Service for User.
 * @author Vinh Truong Canh Thanh, Loc Bui Nhien
*/
@Service
public interface UserService extends UserDetailsService {
    String signUpUser(User appUser);
    String loginUser(User appUser);
    String Forgot(String email, String password);
    void enableAppUser(String email);
    void updatePassword(User user, String password);
    User showPersonalInfor(Long id);
    void editPersonalInfor(Long id, User newUser);

    /**
     * Load user by username
     * @param username String
     * @throws UsernameNotFoundException
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
