package com.example.seraphine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seraphine.repository.AppointmentRepo;
import java.util.List;
import com.example.seraphine.model.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public void saveAppointment(Appointment appointment) {
        this.appointmentRepo.save(appointment);
    }
    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepo.findAll();
    }
    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return this.appointmentRepo.findById(id);
    }
    @Override
    public Appointment updateAppointment(Long id, Appointment new_appointment) {
        return (Appointment) this.appointmentRepo.findById(id).map(appointment -> {
            appointment.setAppointment_reason(new_appointment.getAppointment_reason());
            appointment.setAppointment_description(new_appointment.getAppointment_description());
            appointment.setTimestamp(new_appointment.getTimestamp());
            appointment.setDateBooking(new_appointment.getDateBooking());
            appointment.setLocation(new_appointment.getLocation());
            appointment.setZipCode(new_appointment.getZipCode());
            return null;
        }).orElseGet(() -> {
            new_appointment.setId(id);
            return this.appointmentRepo.save(new_appointment);
        });
    }
    @Override
    public void deleteAppointment(Long id) {
        this.appointmentRepo.delete(appointmentRepo.getById(id));
    }
}


