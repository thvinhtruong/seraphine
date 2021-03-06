package com.example.seraphine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.seraphine.model.ConfirmationToken;

/**
 * Repository interface to access from data and model, also create entity for confirmation token
 * @author Loc Bui Nhien
 */

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepo
        extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,LocalDateTime confirmedAt);

    @Query(value = "SELECT * FROM confirmation_token WHERE app_user_id= ?1", nativeQuery = true)
    List<ConfirmationToken> findByUserId(Long id);
}
