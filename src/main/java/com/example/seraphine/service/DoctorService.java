package com.example.seraphine.service;

import java.util.List;
import java.util.Optional;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.model.Doctor;

public interface DoctorService {
    void saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(long id);
    void updateDoctor(long id, Doctor doctor);
    void deleteDoctor(long id);
    void calculateDistanceToUser(String address);
    List<Doctor> findDoctorWithCriteria(String issues, String address, int distance_to_user);
    List<Appointment> showAvailableAppointments(Long doctor_id);
}
