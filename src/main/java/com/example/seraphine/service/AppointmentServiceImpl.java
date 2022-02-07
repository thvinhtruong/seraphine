package com.example.seraphine.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Set;


import com.example.seraphine.model.Doctor;
import com.example.seraphine.repository.DoctorRepo;
import com.example.seraphine.model.User;
import com.example.seraphine.model.*;
import com.example.seraphine.repository.UserRepo;
import com.example.seraphine.repository.AppointmentRepo;

import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seraphine.repository.AppointmentRepo;
import com.example.seraphine.model.PDFDownloader;
import com.example.seraphine.model.Appointment;


/**
 * Appointment service for appointment logic / operation between client - business
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh
 */

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailSender senderService;

    @Autowired
    private PDFDownloader pdfDownloader;

    /**
     * save appointment to database
     * @param appointment Appointment
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void saveAppointment(Appointment appointment) {
        this.appointmentRepo.save(appointment);
    }

    /**
     * get all appointment from database
     * @author Vinh Truong Canh Thanh
     */
    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepo.findAll();
    }

    /**
     * get appointment based on its id in database
     * @param id Long
     * @author Vinh Truong Canh Thanh
     */
    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return this.appointmentRepo.findById(id);
    }

    /**
     * edit appointment based on id
     * @param id Long
     * @param new_appointment Appointment
     * @author Vinh Truong Canh Thanh
     */
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

    /**
     * delete appointment based on id
     * @param id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void deleteAppointment(Long id) {
        this.appointmentRepo.deleteById(id);
    }

    /**
     * user books appointment
     * @param user_id Long
     * @param appointment_id Long
     * @author Vinh Truong Canh Thanh
     */

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

        if (appointment.getDoctor_id() == null) {
            System.out.println("Appointment is not available due to lack of doctor");
        } else {
            appointment.setStatus(true);
            user.getMyAppointment().add(appointment);
        }
    }

    /**
     * add appointmet to doctor
     * @param doctor_id Long
     * @param new_appointment Appointment
     * @author Vinh Truong Canh Thanh
     */

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

    /**
     * export PDF file / data for appointment based on its id
     * @param id Long
     * @author Tri Nguyen Minh, Vinh Truong Canh Thanh
     */

    @Override
    public void exportAppointmentInfo(Long id) {
        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(id);
        if (appointment_obj.isEmpty()) System.out.println("Appointment not found");
        Appointment appointment = appointment_obj.get();
        String title = "Appointment Information - Seraphine EHealth Service Team";
        String body = appointment.toString();
        try {
            pdfDownloader.export(title, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display all user appointments (appointments that user has booked)
     * @param user_id Long
     * @author Vinh Truong Canh Thanh
     */

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

    /**
     * Display all appointments of a doctor
     * @param doctor_id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public List<Appointment> showDoctorsAppointments(Long doctor_id) {
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();
        return doctor.getAppointments();
    }

    /**
     * Remind user before the appointment
     * @param appointment_id Long
     * @param option String
     * @author Tri Nguyen Minh
     */

    public void remindAppointment(Long appointment_id, String option) {
        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();

        if (appointment.getUser_id() == null) {
            System.out.println("no available user");
        }

        Optional<User> user_obj = this.userRepo.findById(appointment.getUser_id());
        if (user_obj.isEmpty()) {
            System.out.println("user not found");
        }
        User user = user_obj.get();
        senderService.sendScheduledMail(user.getEmail(), appointment, option);
    }
}


