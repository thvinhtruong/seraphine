package com.example.seraphine.controller;

import com.example.seraphine.model.*;
import com.example.seraphine.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

/**
 * The controller for the appointment operations.
 * <p>
 * @author Vinh Truong Canh Thanh, Tri Nguyen Minh, Linh Ngo Phuc
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * Create an appointment and return a string for creating successfully
     * @return String
     */

    @PostMapping("appointment/post")
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "new appointment has been successfully made";
    }

    /**
     * Add a new appointment to doctor list.
     * @param doctor_id Long
     * @param appointment_id Appointment
     * @return an appointment of a doctor
     */
    @GetMapping("doctor/{doctor_id}/appointment/{appointment_id}/add")
    //Need to be fixed
    public ResponseEntity<Void> addAppointmentToDoctor(@PathVariable(value = "doctor_id") Long doctor_id,
                                                       @PathVariable(value = "appointment_id") Long appointment_id) {
        this.appointmentService.addAppointmentToDoctor(doctor_id, appointment_id);
        return ResponseEntity.ok().build();
    }

    /**
     * User book an appointment
     * @param user_id Long
     * @param appointment_id Long
     * @return a status for booking successfully
     */

    @GetMapping("user/{user_id}/appointment/{appointment_id}/book")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    //Requirement: create appointment and user, so we can get appointment id and user id
    public ResponseEntity<Void> bookAppointment(@PathVariable(value = "user_id") Long user_id,
                                                @PathVariable(value = "appointment_id") Long appointment_id) {
        this.appointmentService.bookAppointment(user_id, appointment_id);
        return ResponseEntity.ok().build();
    }

    /**
     * Show all available appointment.
     * @return list of appointment
     */

    @GetMapping("appointment/all")
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    /**
     * Get an appointment based on its id.
     * @param user_id Long
     * @param appointment_id Long
     * @return an appointment
     */

    @GetMapping("user/{user_id}/appointment/{appointment_id}")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable(value = "user_id") Long user_id,
                                                                @PathVariable(value = "appointment_id") Long appointment_id) {
        Optional<Appointment> appointment = this.appointmentService.getAppointmentById(user_id, appointment_id);
        return ResponseEntity.ok().body(appointment);
    }

    /**
     * Get all appointment of a specific user based in user id.
     * @param user_id Long
     * @return set of appointment for that user
     */

    @GetMapping("user/{user_id}/appointment/all")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public List<Appointment> getAllUserAppointments(@PathVariable(value = "user_id") Long user_id) {
        return this.appointmentService.showUserAppointments(user_id);
    }

    /**
     * Get all appointment of a specific doctor based on doctor id.
     * @param doctor_id Long
     * @return list of appointment for that doctor
     */

    @GetMapping("doctor/{doctor_id}/appointment/all")
    public List<Appointment> getAllDoctorAppointment(@PathVariable(value = "doctor_id") Long doctor_id) {
        return this.appointmentService.showDoctorsAppointments(doctor_id);
    }

    /**
     * Shift an appointment
     * @param appointment_id Long
     * @param new_appointment Appointment
     * @return String
     */

    @PutMapping("user/{user_id}/appointment/{appointment_id}/shift")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public String shiftAppointment( @PathVariable(value = "user_id") Long user_id,
                                    @PathVariable(value = "appointment_id") Long appointment_id,
                                    @RequestBody Appointment new_appointment) {
        this.appointmentService.updateAppointment(user_id, appointment_id, new_appointment);
        return "all changes about appointment have been saved";
    }

    /**
     * Delete an appointment
     * @param appointment_id Long
     * @return status for empty appointment
     */

    @DeleteMapping("user/{user_id}/appointment/{appointment_id}/cancel")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(value = "user_id") Long user_id,
                                                  @PathVariable(value = "appointment_id") Long appointment_id) {
        this.appointmentService.deleteAppointment(user_id, appointment_id);
        return ResponseEntity.ok().build();
    }

    /**
     * Create pdf and download for an appointment based on id.
     * @param user_id Long
     * @param appointment_id Long
     * @return String
     */

    @GetMapping("user/{user_id}/appointment/{appointment_id}/generate_pdf")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public String exportAppointment(@PathVariable(value = "user_id") Long user_id,
                                    @PathVariable(value = "appointment_id") Long appointment_id) {
        this.appointmentService.exportAppointmentInfo(user_id, appointment_id);
        return "Printed successfully";
    }

    /**
     * Remind user for the coming appointment
     * @param user_id Long
     * @param appointment_id Long
     * @param remind_option String
     * @return String
     */
    @GetMapping("user/{user_id}/appointment/{appointment_id}/remind")
    @PreAuthorize("#user_id == authentication.getPrincipal().getId()")
    public String remindAppointment(@PathVariable(value = "user_id") Long user_id,
                                    @PathVariable(value = "appointment_id") Long appointment_id,
                                    @RequestParam String remind_option) {
        this.appointmentService.remindAppointment(user_id, appointment_id, remind_option);
        return "Remind appointment successfully!";
    }
}
