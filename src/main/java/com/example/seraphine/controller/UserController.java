package com.example.seraphine.controller;

import com.example.seraphine.model.User;
import com.example.seraphine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for User.
 * @author Tri Nguyen Minh
 * */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * View personal profile
     * @author Tri Nguyen Minh
     * @param user_id Long
     * @return User's profile
     * */
    @GetMapping("/{user_id}/view_profile")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public User showPersonalInfo(@PathVariable(value = "user_id") Long user_id){
        return this.userService.showPersonalInfor(user_id);
    }
    /**
     * Edit personal profile
     * @author Tri Nguyen Minh
     * @param user_id Long
     * @param new_user User
     * */
    @PostMapping("/{user_id}/edit")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public ResponseEntity<Void> editPersonalInfo(@PathVariable(value = "user_id") Long user_id,
                                                 @RequestBody User new_user){
        this.userService.editPersonalInfor(user_id, new_user);
        return ResponseEntity.ok().build();
    }
}
