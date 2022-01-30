package com.example.seraphine.service;

import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUser() { return userRepo.findAll(); }

    @Override
    public Optional<User> getUserById(Long id) { return userRepo.findById(id); }

    @Override
    public User updateUser(Long id, User newUser) {
        return (User) this.userRepo.findById(id).map(user -> {
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

    @Override
    public void deleteUser(Long id) throws Exception {
        boolean exists = userRepo.existsById(id);
        if (!exists){
            throw new Exception("User with ID " + id + " does not exists");
        }
        userRepo.deleteById(id);
    }
}
