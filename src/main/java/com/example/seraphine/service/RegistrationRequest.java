package com.example.seraphine.service;

import lombok.*;

/**
 * The request for the registration service.
 * <p>
 * @author Loc Bui Nhien
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String userName;
    private final String password;
    private final String DateOfBirth;
    private final String insuranceType;
    private final String insuranceName;
}