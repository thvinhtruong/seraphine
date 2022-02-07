package com.example.seraphine.service;

import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Basic operation / logic for admin
 * @author Tri Nguyen Minh
 */

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepo userRepo;

    /**
     * get all user from database
     * @author Tri Nguyen Minh
     */

    @Override
    public List<User> getAllUsers() { return userRepo.findAll(); }

    /**
     * get specific user by id
     * @param id Long
     * @author Tri Nguyen Minh
     */

    @Override
    public Optional<User> getUserById(Long id) { return userRepo.findById(id); }

    /**
     * delete user from database
     * @param id Long
     * @author Tri Nguyen Minh
     */

    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id); }

    /**
     * Update specific user using user's id
     * @author Tri Nguyen Minh
     * @param id Long
     * @param newUser User
     */
    @Override
    public void updateUser(Long id, User newUser) {
        this.userRepo.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setInsuranceName(newUser.getInsuranceName());
            user.setInsuranceType(newUser.getInsuranceType());
            return null;
        }).orElseGet(()->{
            newUser.setId(id);
            return this.userRepo.save(newUser);
        });
    }
}

















