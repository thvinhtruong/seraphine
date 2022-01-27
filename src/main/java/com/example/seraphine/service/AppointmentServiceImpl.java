package com.example.seraphine.service;

import java.util.HashSet;
import java.util.Optional;

import com.example.seraphine.repository.DoctorRepo;
import com.example.seraphine.model.User;
import com.example.seraphine.model.Doctor;
import com.example.seraphine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seraphine.repository.AppointmentRepo;
import com.example.seraphine.model.Appointment;

import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserRepo userRepo;

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
            appointment.setStart_time(new_appointment.getStart_time());
            appointment.setEnd_time(new_appointment.getEnd_time());
            appointment.setDateBooking(new_appointment.getDateBooking());
            appointment.setStatus(new_appointment.isBooked());
            return null;
        }).orElseGet(() -> {
            new_appointment.setId(id);
            return this.appointmentRepo.save(new_appointment);
        });
    }
    @Override
    public void deleteAppointment(Long id) {
        this.appointmentRepo.deleteById(id);
    }

    @Override
    public Appointment bookAppointment(Long user_id, Appointment new_appointment) {
        new_appointment.setStatus(true);
        Set<Appointment> appointment_list = new HashSet<>();
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("user not found");
        }
        User user = user_obj.get();
        Appointment user_appointment =  this.appointmentRepo.save(new_appointment);
        appointment_list.add(user_appointment);
        user.setMyAppointment(appointment_list);
        return user_appointment;
    }

    @Override
    public Appointment addDoctorAppointment(Long doctor_id, Appointment appointment) {
        Set<Appointment> appointments_list = new HashSet<>();
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();
        Appointment doctor_appointment = this.appointmentRepo.save(appointment);
        appointments_list.add(doctor_appointment);
        doctor.setAppointments(appointments_list);
        return doctor_appointment;
    }

    @Override
    public List<Appointment> showAllDoctorsAppointments(Long id) {
        return this.appointmentRepo.findByDoctorId(id);
    }

    @Override
    public List<Appointment> showAllUsersAppointments(Long id) {
        return this.appointmentRepo.findByUserId(id);
    }
}


