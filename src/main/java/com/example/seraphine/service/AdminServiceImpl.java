package com.example.seraphine.service;

import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implements abstract methods from AdminService interface
 * @author Tri Nguyen Minh
 */
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepo userRepo;

    /**
     * Query all user from the repository
     * @author Tri Nguyen Minh
     * @return
     */
    @Override
    public List<User> getAllUsers() { return userRepo.findAll(); }
    /**
     * Query user from the repository using id
     * @author Tri Nguyen Minh
     * @param id
     * @return
     */
    @Override
    public Optional<User> getUserById(Long id) { return userRepo.findById(id); }
    /**
     * Delete user from the repository using user's id
     * @author Tri Nguyen Minh
     * @param id
     * @return
     */
    @Override
    public void deleteUser(Long id) { this.userRepo.deleteById(id); }
    /**
     * Update specific user using user's id
     * @author Tri Nguyen Minh
     * @param id
     * @param newUser
     * @return
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

















