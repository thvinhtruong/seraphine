package com.example.seraphine.repository;

import java.util.Optional;

import com.example.seraphine.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface to access from data and model, also create entity for user
 * @author Loc Bui Nhien
 */

@Transactional(readOnly = true)
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Query("SELECT c FROM User c WHERE c.email = ?1")
    User FindByEmail(String email);

    @Bean
    public User findByResetPasswordToken(String token);

}
