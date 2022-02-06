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
<<<<<<< HEAD
    void bookAppointment(Long user_id, Long appointment_id);
    Appointment addAppointmentToDoctor(Long doctor_id, Appointment new_appointment);
    void exportAppointmentInfo(Long id);
    Set<Appointment> showUserAppointments(Long user_id);
    List<Appointment> showDoctorsAppointments(Long doctor_id);
=======
    Appointment bookAppointment(Long user_id, Appointment new_appointment);
    Appointment addDoctorAppointment(Long doctor_id, Appointment appointment);
    void exportAppointmentInfo(Long id);
    void remindAppointment(Long appointment_id, String option);
>>>>>>> refs/remotes/origin/main
}
