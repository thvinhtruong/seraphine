package com.example.seraphine.controller;

import com.example.seraphine.model.User;
import com.example.seraphine.model.UserRole;
import com.example.seraphine.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    private boolean authenticator(User user){
        //TRUE if user role is ADMIN, FALSE if user role is USER
        return user.getUserRole().equals(UserRole.ADMIN);
    }
    public User user;

    @GetMapping("api/v1/user/all")
    public List<User> getAllUsers() throws Exception {
        if (!authenticator(user)){
            throw new Exception("This user is not ADMIN to perform this action");
        }
        else{
            return this.adminService.getAllUser();
        }
    }

    @GetMapping("api/v1/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id) throws Exception {
        if (!authenticator(user)){
            throw new Exception("This user is not ADMIN to perform this action!");
        }
        else{
            Optional<User> user = this.adminService.getUserById(id);
            return ResponseEntity.ok().body(user);
        }
    }

    @PutMapping("api/v1/user/{id}")
    public String editUser(@PathVariable(value = "id") Long id, @RequestBody User newUser) throws Exception {
        if (!authenticator(user)){
            throw new Exception("This user is not ADMIN to perform this action!");
        }
        else{
            this.adminService.updateUser(id, newUser);
            return "All changes to user has been applied";
        }
    }

    @DeleteMapping("api/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) throws Exception {
        if (!authenticator(user)){
            throw new Exception("This user is not ADMIN to perform this action!");
        }
        else {
            this.adminService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
    }
}
