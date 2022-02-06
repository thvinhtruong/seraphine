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

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return this.adminService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") Long id) {
        Optional<User> user = this.adminService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, @RequestBody User newUser) {
        this.adminService.updateUser(id, newUser);
        return "All changes to user has been applied";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        this.adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
