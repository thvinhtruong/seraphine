package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "Doctor")
@NoArgsConstructor

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String specialization;

    public Doctor(String fistName, String lastName, String gender, String address, String specialization) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFistName() {
        return firstName;
    }

    public void setFistName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctor_id=" + id +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
