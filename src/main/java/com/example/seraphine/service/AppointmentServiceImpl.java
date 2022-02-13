package com.example.seraphine.service;

import java.util.Optional;
import java.util.List;


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
import com.example.seraphine.model.PDFDownloader;
import com.example.seraphine.model.Appointment;


/**
 * Appointment service for appointment logic / operation between client - business
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh, Linh Ngo Phuc
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
     * @author Vinh Truong Canh Thanh, Linh Ngo Phuc
     */
    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepo.findAll();
    }

    /**
     * get appointment based on its id in database
     * @param user_id Long
     * @param appointment_id Long
     * @author Vinh Truong Canh Thanh, Linh Ngo Phuc
     */
    @Override
    public Optional<Appointment> getAppointmentById(Long user_id, Long appointment_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()){
            System.out.println("User not found!");
        }
        User user = user_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()){
            System.out.println("Appointment not found!");
        }
        Appointment appointment = appointment_obj.get();
        List<Appointment> appointments = user.getMyAppointment();
        if (!appointments.contains(appointment)){
            throw new IllegalStateException("User " + user_id + " don't have permission to access appointment " + appointment_id);
        }

        return Optional.of(appointment);
    }

    /**
     * edit appointment based on id
     * @param user_id Long
     * @param appointment_id Long
     * @param new_appointment Appointment
     * @author Vinh Truong Canh Thanh, Linh Ngo Phuc
     */
    @Override
    public void updateAppointment(Long user_id, Long appointment_id, Appointment new_appointment) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()){
            System.out.println("User not found!");
        }
        User user = user_obj.get();
        List<Appointment> appointments = user.getMyAppointment();
        boolean appointmentExists = false;
        for (Appointment a : appointments){
            if (a.getId().equals(appointment_id)) {
                appointmentExists = true;
                break;
            }
        }
        if (!appointmentExists){
            System.out.println("User " + user_id + " don't have permission to access appointment " + appointment_id);
        }
        new_appointment.setStatus(true);

        this.appointmentRepo.findById(appointment_id).map(appointment -> {
            appointment.setAppointment_reason(new_appointment.getAppointment_reason());
            appointment.setStart_time(new_appointment.getStart_time());
            appointment.setEnd_time(new_appointment.getEnd_time());
            appointment.setDateBooking(new_appointment.getDateBooking());
            appointment.setStatus(true);
            return null;
        }).orElseGet(() -> {
            new_appointment.setId(appointment_id);
            return this.appointmentRepo.save(new_appointment);
        });
        senderService.sendEmail(user.getEmail(), "[SERAPHINE] Appointment shifting - Seraphine EHealth Service Team", "" +
                "Hi " + user.getUsername() + ",\nYour appointment " + appointment_id + " has shifted successfully" +
                "\nYour health care service\nSeraphine");
    }

    /**
     * delete appointment based on id
     * @param user_id Long
     * @param appointment_id Long
     * @author Vinh Truong Canh Thanh, Linh Ngo Phuc
     */
    @Override
    public void deleteAppointment(Long user_id, Long appointment_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()){
            System.out.println("User not found!");
        }
        User new_user = user_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()){
            System.out.println("Appointment not found!");
        }
        Appointment new_appointment = appointment_obj.get();
        List<Appointment> appointments = new_user.getMyAppointment();
        if (!appointments.contains(new_appointment)){
            throw new IllegalStateException("User " + user_id + " don't have permission to access appointment " + appointment_id);
        }
        appointments.removeIf(appointment ->
                appointment.getAppointment_reason().equals(new_appointment.getAppointment_reason()) &&
                appointment.getStart_time().equals(new_appointment.getStart_time()) &&
                appointment.getEnd_time().equals(new_appointment.getEnd_time()) &&
                appointment.getDateBooking().equals(new_appointment.getDateBooking()));

        new_appointment.setStatus(false);

        this.userRepo.findById(user_id).map(user -> {
            user.setFirstName(new_user.getFirstName());
            user.setLastName(new_user.getLastName());
            user.setEmail(new_user.getEmail());
            user.setDateOfBirth(new_user.getDateOfBirth());
            user.setInsuranceName(new_user.getInsuranceName());
            user.setInsuranceType(new_user.getInsuranceType());
            user.setMyAppointment(new_user.getMyAppointment());
            return null;
        }).orElseGet(() -> {
            new_user.setId(user_id);
            return this.userRepo.save(new_user);
        });

        this.appointmentRepo.findById(appointment_id).map(appointment -> {
            appointment.setAppointment_reason(new_appointment.getAppointment_reason());
            appointment.setStart_time(new_appointment.getStart_time());
            appointment.setEnd_time(new_appointment.getEnd_time());
            appointment.setDateBooking(new_appointment.getDateBooking());
            appointment.setStatus(new_appointment.isBooked());
            return null;
        }).orElseGet(() -> {
            new_appointment.setId(appointment_id);
            return this.appointmentRepo.save(new_appointment);
        });
        senderService.sendEmail(new_user.getEmail(), "[SERAPHINE] Appointment cancellation - Seraphine EHealth Service Team", "" +
                "Hi " + new_user.getUsername() + ",\nYour appointment " + appointment_id + " has successfully been cancelled." +
                "\nYour health care service\nSeraphine");
    }

    /**
     * user books appointment
     * @param user_id Long
     * @param appointment_id Long
     * @author Vinh Truong Canh Thanh, Tri Nguyen Minh
     */
    @Override
    public void bookAppointment(Long user_id, Long appointment_id) {
        if (user_id.equals(1L)){
            throw new IllegalStateException("No permission!");
        }
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("User not found");
        }
        User new_user = user_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();
        /*
        if (appointment.getDoctor_id() == null) {
            System.out.println("Appointment is not available due to lack of doctor");
        }*/
        appointment.setStatus(true);
        appointment.setDoctor_id(null);
        new_user.getMyAppointment().add(appointment);

        this.userRepo.findById(user_id).map(user -> {
            user.setFirstName(new_user.getFirstName());
            user.setLastName(new_user.getLastName());
            user.setEmail(new_user.getEmail());
            user.setDateOfBirth(new_user.getDateOfBirth());
            user.setInsuranceName(new_user.getInsuranceName());
            user.setInsuranceType(new_user.getInsuranceType());
            user.setMyAppointment(new_user.getMyAppointment());
            return null;
        }).orElseGet(()->{
            new_user.setId(user_id);
            return this.userRepo.save(new_user);
        });
    }

    /**
     * add appointment to doctor
     * @param doctor_id Long
     * @param appointment_id Appointment
     * @author Vinh Truong Canh Thanh, Tri Nguyen Minh
     */
    @Override
    public void addAppointmentToDoctor(Long doctor_id, Long appointment_id) {
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("Doctor not found");
        }
        Doctor new_doctor = doctor_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();

        if (new_doctor.getAppointments().contains(appointment)) {
            throw new IllegalStateException("Appointment exists in doctor profile with ID " + doctor_id);
        }
        appointment.setDoctor_id(doctor_id);
        new_doctor.getAppointments().add(appointment);

        this.doctorRepo.findById(doctor_id).map(doctor -> {
            doctor.setFirstName(new_doctor.getFirstName());
            doctor.setLastName(new_doctor.getLastName());
            doctor.setGender(new_doctor.getGender());
            doctor.setEmails(new_doctor.getEmails());
            doctor.setAddress(new_doctor.getAddress());
            doctor.setSpecialization(new_doctor.getSpecialization());
            doctor.setDistance_to_user(new_doctor.getDistance_to_user());
            doctor.setIssue_covered(new_doctor.getIssue_covered());
            doctor.setAppointments(new_doctor.getAppointments());
            return null;
        }).orElseGet(() -> {
            new_doctor.setId(doctor_id);
            return this.doctorRepo.save(new_doctor);
        });
    }

    /**
     * export PDF file / data for appointment based on its id
     * @param user_id Long
     * @param appointment_id Long
     * @author Tri Nguyen Minh, Vinh Truong Canh Thanh
     */

    @Override
    public void exportAppointmentInfo(Long user_id, Long appointment_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("user not found");
        }
        User user = user_obj.get();

        Optional<Appointment> appointment_obj = this.appointmentRepo.findById(appointment_id);
        if (appointment_obj.isEmpty()) {
            System.out.println("Appointment not found");
        }
        Appointment appointment = appointment_obj.get();
        List<Appointment> appointments = user.getMyAppointment();
        if (!appointments.contains(appointment)){
            throw new IllegalStateException("User " + user_id + " don't have permission to access information about appointment "
                    + appointment_id);
        }
        String title = "Appointment Information - Seraphine EHealth Service Team";
        String body = "Appointment ID: " + appointment_id + "\nAppointment reason: " + appointment.getAppointment_reason() +
                "\nAppointment description: " + "\nAppointment start time: " +
                appointment.getStart_time() + "\nAppointment end time: " + appointment.getEnd_time() + "\nDate booking: "
                + appointment.getDateBooking();
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
    public List<Appointment> showUserAppointments(Long user_id) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("user not found");
        }
        return user_obj.get().getMyAppointment();
    }

    /**
     * Display all appointments of a doctor
     * @param doctor_id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public List<Appointment> showDoctorsAppointments(Long doctor_id) {
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()){
            System.out.println("No doctor found");
        }
        return doctor_obj.get().getAppointments();
    }

    /**
     * Remind user before the appointment
     * @param appointment_id Long
     * @param option String
     * @author Tri Nguyen Minh
     */

    public void remindAppointment(Long user_id, Long appointment_id, String option) {
        Optional<User> user_obj = this.userRepo.findById(user_id);
        if (user_obj.isEmpty()) {
            System.out.println("User not found");
        }
        User user = user_obj.get();
        boolean appointmentExists = false;
        List<Appointment> appointments = user.getMyAppointment();
        for (Appointment appointment : appointments){
            if (appointment.getId().equals(appointment_id)){
                senderService.sendScheduledMail(user.getUsername(), user.getEmail(), appointment, option);
                appointmentExists = true;
                break;
            }
        }
        if (!appointmentExists){
            System.out.println("User " + user_id + " don't have permission to access appointment " + appointment_id);
        }
    }
}


