package com.example.seraphine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Encode password for security purpose
 * @author Loc Bui Nhien
 */
@Configuration
public class PasswordEncoder {

    /**
     * Encode password using bcrypt password encoding.
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}