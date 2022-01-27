package com.example.seraphine.service;

import com.example.seraphine.model.Appointment;
<<<<<<< HEAD
import com.example.seraphine.model.Doctor;
=======

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
>>>>>>> 17a5db440cef197899420523ac6394b4b600ed5a
import java.util.Optional;
import java.util.List;

public interface AppointmentService {
    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
<<<<<<< HEAD
    Appointment bookAppointment(Long user_id, Appointment new_appointment);
    Appointment addDoctorAppointment(Long doctor_id, Appointment appointment);
    List<Appointment> showAllDoctorsAppointments(Long id);
    List<Appointment> showAllUsersAppointments(Long id);
=======
    void exportAppointmentInfo(Long id);
>>>>>>> 17a5db440cef197899420523ac6394b4b600ed5a
}
