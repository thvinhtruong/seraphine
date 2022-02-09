package com.example.seraphine.controller;

import com.example.seraphine.model.*;
import com.example.seraphine.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;
import java.util.Set;

/**
 * The controller for the appointment operations.
 * <p>
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * Create an appointment and return a string for creating successfully
     * <p>
     * @return String
     */

    @PostMapping("appointment/post")
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "new appointment has been successfully made";
    }

    /**
     * Add a new appointment to doctor list.
     * <p>
     * @param doctor_id Long
     * @param appointment_id Long
     * @return an appointment of a doctor
     */

    @GetMapping("doctor/appointment/add/{doctorId}/{appointmentId}")
    //Need to be fixed
    public ResponseEntity<Void> addAppointmentToDoctor(@PathVariable(value = "doctorId") Long doctor_id,
                                                       @PathVariable(value = "appointmentId") Long appointment_id) {
        this.appointmentService.addAppointmentToDoctor(doctor_id, appointment_id);
        return ResponseEntity.ok().build();
    }

    /**
     * User book an appointment
     * <p>
     * @param userId Long
     * @param appointmentId Long
     * @return a status for booking successfully
     */

    @GetMapping("user/appointment/add/{userId}/{appointmentId}")
    //Requirement: create appointment and user, so we can get appointment id and user id
    public ResponseEntity<Void> bookAppointment(@PathVariable(value = "userId") Long userId,
                                                @PathVariable(value = "appointmentId") Long appointmentId) {
        this.appointmentService.bookAppointment(userId, appointmentId);
        return ResponseEntity.ok().build();
    }

    /**
     * Show all available appointment.
     * <p>
     * @return list of appointment
     */

    @GetMapping("appointment/all")
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    /**
     * Get an appointment based on its id.
     * <p>
     * @param id Long
     * @return an appointment
     */

    @GetMapping("appointment/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable(value = "id") Long id) {
        Optional<Appointment> appointment = this.appointmentService.getAppointmentById(id);
        return ResponseEntity.ok().body(appointment);
    }

    /**
     * Get all appointment of a specific user based in user id.
     * <p>
     * @param user_id Long
     * @return set of appointment for that user
     */

    @GetMapping("user/appointment/all/{id}")
    public List<Appointment> getAllUserAppointments(@PathVariable(value = "id") Long user_id) {
        return this.appointmentService.showUserAppointments(user_id);
    }

    /**
     * Get all appointment of a specific doctor based on doctor id.
     * <p>
     * @param doctor_id Long
     * @return list of appointment for that doctor
     */

    @GetMapping("doctor/appointment/all/{id}")
    public List<Appointment> getAllDoctorAppointment(@PathVariable(value = "id") Long doctor_id) {
        return this.appointmentService.showDoctorsAppointments(doctor_id);
    }

    /**
     * Shift an appointment
     * <p>
     * @param appointment_id Long
     * @param new_appointment Appointment
     * @return String
     */

    @PutMapping("appointment/{id}")
    public String shiftAppointment(@PathVariable(value = "id") Long appointment_id,
                                    @RequestBody Appointment new_appointment) {
        this.appointmentService.updateAppointment(appointment_id, new_appointment);
        return "all changes about appointment have been saved";
    }

    /**
     * Delete an appointment
     * <p>
     * @param appointment_id Long
     * @return status for empty appointment
     */

    @DeleteMapping("appointment/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(value = "id") Long appointment_id) {
        this.appointmentService.deleteAppointment(appointment_id);
        return ResponseEntity.ok().build();
    }

    /**
     * Create pdf and download for an appointment based on id.
     * <p>
     * @param id Long
     * @return String
     */

    @GetMapping("appointment/pdf/generate/{id}")
    public String exportAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.exportAppointmentInfo(id);
        return "Printed successfully";
    }

    /**
     * Remind user for the coming appointment
     * <p>
     * @param id Long
     * @param remind_option String
     * @return String
     */

    @GetMapping("appointment/remind/{id}")
    public String remindAppointment(@PathVariable(value = "id") Long id, @RequestParam String remind_option) {
        this.appointmentService.remindAppointment(id, remind_option);
        return "Remind appointment successfully!";
    }
}
