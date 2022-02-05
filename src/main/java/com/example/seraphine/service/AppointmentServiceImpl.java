package com.example.seraphine.service;

import java.util.*;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.repository.DoctorRepo;
import com.example.seraphine.model.User;
import com.example.seraphine.repository.UserRepo;
import java.io.IOException;

import com.example.seraphine.model.PDFDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seraphine.repository.AppointmentRepo;
import com.example.seraphine.model.Appointment;

import javax.print.Doc;

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
    public void bookAppointment(Long user_id, Long appointment_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            // will throw exception here
            System.out.println("user not found");
        }
        User user = user_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Appointment appointment = appointment_obj.get();

        if (appointment.getDoctorId() == null) {
            System.out.println("Appointment is not available due to lack of doctor");
        } else {
            appointment.setStatus(true);
            user.getMyAppointment().add(appointment);
        }
    }

    @Override
    public Appointment addAppointmentToDoctor(Long doctor_id, Appointment new_appointment) {
        List<Appointment> appointments_ls = new ArrayList<>();
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();

        Appointment booking = this.appointmentRepo.save(new_appointment);
        appointments_ls.add(booking);
        doctor.setAppointments(appointments_ls);

        return booking;
    }

    @Override
    public void exportAppointmentInfo(Long id) {
        PDFDownloader downloader = new PDFDownloader();
        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();
        String title = "Appointment Information - Seraphine EHealth Service Team";
        String body = appointment.toString();
        try {
            downloader.export(title, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Appointment> showUserAppointments(Long user_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            // will throw exception here
            System.out.println("user not found");
        }
        User user = user_obj.get();
        return user.getMyAppointment();
    }

    @Override
    public List<Appointment> showDoctorsAppointments(Long doctor_id) {
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();
        return doctor.getAppointments();
    }
}


