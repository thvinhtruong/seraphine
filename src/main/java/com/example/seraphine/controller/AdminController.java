package com.example.seraphine.controller;

import java.util.List;
import java.util.Optional;
import com.example.seraphine.model.User;
import com.example.seraphine.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

/** 
 * Controller for Admin.
 * @author Tri Nguyen Minh
 * */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Get all users.
     * @return List of user
     */
    @GetMapping("/user/get/all")
    public List<User> getAllUsers() {
        return this.adminService.getAllUsers();
    }

    /**
     * Get user by id.
     * @param id Long
     * @return user body in JSON format
     */
    @GetMapping("/user/get/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id) {
        Optional<User> user = this.adminService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    /**
     * Update user.
     * @param id Long
     * @param newUser User
     * @return String
     */
    @PutMapping("/user/edit/{id}")
    public String editUser(@PathVariable(value = "id") Long id, @RequestBody User newUser) {
        this.adminService.updateUser(id, newUser);
        return "All changes to user has been applied";
    }

    /**
     * Delete user.
     * @param id Long
     * @return void
     */
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") long id) {
        this.adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
