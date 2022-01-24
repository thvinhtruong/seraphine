package com.example.seraphine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ForgotPasswordToken {

    @SequenceGenerator(
            name = "forgot_password_token_sequence",
            sequenceName = "forgot_password_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "forgot_password_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private String password;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private User appUser;

    public ForgotPasswordToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, String password) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.password=password;
    }
}