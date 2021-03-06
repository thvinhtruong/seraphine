package com.example.seraphine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.seraphine.model.ForgotPasswordToken;

/**
 * Repository interface to access from data and model, also create entity for password token
 * @author Loc Bui Nhien
 */

@Repository
@Transactional(readOnly = true)
public interface ForgotPasswordTokenRepo
        extends JpaRepository<ForgotPasswordToken, Long> {

    Optional<ForgotPasswordToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ForgotPasswordToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,LocalDateTime confirmedAt);
}
