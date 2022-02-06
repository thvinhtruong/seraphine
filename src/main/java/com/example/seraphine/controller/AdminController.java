package com.example.seraphine.controller;

import com.example.seraphine.model.User;
import com.example.seraphine.service.AdminService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 
 * Controller for Admin.
 * @authur Tri Nguyen Minh
 * */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Get all users.
     * @return
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return this.adminService.getAllUsers();
    }

    /**
     * Get user by id.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id) {
        Optional<User> user = this.adminService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    /**
     * Update user.
     * @param id
     * @param newUser
     * @return
     */
    @PutMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, @RequestBody User newUser) {
        this.adminService.updateUser(id, newUser);
        return "All changes to user has been applied";
    }

    /**
     * Delete user.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        this.adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
