package com.example.seraphine.repository;

import com.example.seraphine.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccessTokenRepo extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

    @Query(value = "SELECT * FROM access_token WHERE app_user_id= ?1", nativeQuery = true)
    List<AccessToken> findByUserId(Long id);
}
