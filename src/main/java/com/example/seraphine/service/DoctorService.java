package com.example.seraphine.service;

import java.util.List;
import java.util.Optional;

import com.example.seraphine.model.Doctor;

public interface DoctorService {
    void saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(int id);
    Doctor updateDoctor(int id, Doctor doctor);
    void deleteDoctor(int id);


}
