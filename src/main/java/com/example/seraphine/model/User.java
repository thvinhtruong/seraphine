package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "User")
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
    private String insuranceType;
    private String insuranceName;

    public User(String firstName, String lastName, String email, String username, String password, String dateOfBirth, String insuranceType, String insuranceName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFistName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setFistName(String fistName) {
        this.firstName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    @Override
    public String toString() {
        return "User{" +
                "fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                '}';
    }
}
