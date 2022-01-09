package com.example.seraphine.model;

import java.util.UUID;

public class Doctor {
    private UUID doctor_id;
    private String fistName;
    private String lastName;
    private String gender;
    private String address;
    private String specialization;

    public Doctor(UUID doctor_id, String fistName, String lastName, String gender, String address, String specialization) {
        this.doctor_id = doctor_id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.specialization = specialization;
    }

    public UUID getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(UUID doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
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
                "doctor_id=" + doctor_id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
