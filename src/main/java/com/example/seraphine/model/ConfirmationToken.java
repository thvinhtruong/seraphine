package com.example.seraphine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The class is a model for the ConfirmationToken entity.
 * <p>
 * @author Loc Bui Nhien
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    /**
     * Generate the id of the token.
     */
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private User appUser;

    /**
     * Create a new confirmation token unique for the user. 
     *  
     * @param token
     * @param createdAt
     * @param expiresAt
     * @param appUser
     */
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}