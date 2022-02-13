package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;

import java.util.Optional;
import java.util.List;

/**
 * Service interface to access from data and model, also create entity for appointment
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh, Linh Ngo Phuc
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
