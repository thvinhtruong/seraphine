package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.model.User;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.List;
import java.util.Set;

public interface AppointmentService {
    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    void bookAppointment(Long user_id, Long appointment_id);
    void addAppointmentToDoctor(Long doctor_id, Long appointment_id);
    void exportAppointmentInfo(Long id);
    List<Appointment> showUserAppointments(Long user_id);
    List<Appointment> showDoctorsAppointments(Long doctor_id);
    void remindAppointment(Long appointment_id, String option);
}
