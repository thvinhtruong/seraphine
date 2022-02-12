package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.model.User;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.List;
import java.util.Set;

/**
 * Service interface to access from data and model, also create entity for appointment
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh
 */
public interface AppointmentService {
    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long user_id, Long appointment_id);
    void updateAppointment(Long user_id, Long appointment_id, Appointment appointment);
    void deleteAppointment(Long user_id, Long appointment_id);
    void bookAppointment(Long user_id, Long appointment_id);
    void addAppointmentToDoctor(Long doctor_id, Long appointment_id);
    void exportAppointmentInfo(Long user_id, Long appointment_id);
    List<Appointment> showUserAppointments(Long user_id);
    List<Appointment> showDoctorsAppointments(Long doctor_id);
    void remindAppointment(Long user_id, Long appointment_id, String option);
}
