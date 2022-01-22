package com.example.seraphine.controller;

import com.example.seraphine.model.User;
import com.example.seraphine.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("api/v1/user/all")
    public List<User> getAllUsers(){ return this.adminService.getAllUser(); }

    @GetMapping("api/v1/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id){
        Optional<User> user = this.adminService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("api/v1/user/{id}")
    public String editUser(@PathVariable(value = "id") Long id, @RequestBody User newUser){
        this.adminService.updateUser(id, newUser);
        return "All changes to user has been applied";
    }

    @DeleteMapping("api/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id){
        this.adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
