package com.example.seraphine.service;

import com.example.seraphine.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Provides methods that admin can use to do basic operations with user's information in the database
 * @author Tri Nguyen Minh
 */
public interface AdminService {
    /**
     * Query all user from the repository
     * @author Tri Nguyen Minh
     */
    List<User> getAllUsers();
    /**
     * Query user from the repository using id
     * @author Tri Nguyen Minh
     * @param id Long
     */
    Optional<User> getUserById(Long id);
    /**
     * Update specific user using user's id
     * @author Tri Nguyen Minh
     * @param id Long
     * @param newUser User
     */
    void updateUser(Long id, User newUser);
    /**
     * Delete user from the repository using user's id
     * @author Tri Nguyen Minh
     * @param id Long
     */
    void deleteUser(long id);
    /**
     * create admin
     * @author Tri Nguyen Minh
     * @param admin User
     */
    void createAdmin(User admin);
}
