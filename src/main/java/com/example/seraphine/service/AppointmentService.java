package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.model.Doctor;
import java.util.Optional;
import java.util.List;

public interface AppointmentService {
    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    Appointment bookAppointment(Long user_id, Appointment new_appointment);
    Appointment addDoctorAppointment(Long doctor_id, Appointment appointment);
    List<Appointment> showAllDoctorsAppointments(Long id);
    List<Appointment> showAllUsersAppointments(Long id);
}
