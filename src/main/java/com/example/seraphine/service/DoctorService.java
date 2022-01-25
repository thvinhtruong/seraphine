package com.example.seraphine.service;

import java.util.List;
import java.util.Optional;
import com.example.seraphine.model.Doctor;

import javax.print.Doc;

public interface DoctorService {
    void saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(long id);
    void updateDoctor(long id, Doctor doctor);
    void deleteDoctor(long id);
    List<Doctor> findDoctorWithCriteria(String issues, int distance_to_user);
}
