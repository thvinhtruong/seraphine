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
 * Controller for Appointment.
 * @authur Vinh Truong Canh Thanh, Tri Nguyen Minh
 * */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("appointment/post")
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "new appointment has been successfully made";
    }

    @PostMapping("doctor/appointment/add/{id}")
    public Appointment addAppointmentToDoctor(@PathVariable(value = "id") Long doctor_id, @RequestBody Appointment appointment) {
        return this.appointmentService.addAppointmentToDoctor(doctor_id, appointment);
    }

    @GetMapping("user/appointment/{userId}/{appointmentId}")
    public ResponseEntity<Void> bookAppointment(@PathVariable(value = "userId") Long userId,
                                                @PathVariable(value = "appointmentId") Long appointmentId) {
        this.appointmentService.bookAppointment(userId, appointmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("appointment/all")
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    @GetMapping("appointment/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable(value = "id") Long id) {
        Optional<Appointment> appointment = this.appointmentService.getAppointmentById(id);
        return ResponseEntity.ok().body(appointment);
    }


    @GetMapping("user/appointment/all/{id}")
    public Set<Appointment> getAllUserAppointments(@PathVariable(value = "id") Long id) {
        return this.appointmentService.showUserAppointments(id);
    }

    @GetMapping("doctor/appointment/all/{id}")
    public List<Appointment> getAllDoctorAppointment(@PathVariable(value = "id") Long id) {
        return this.appointmentService.showDoctorsAppointments(id);
    }

    @PutMapping("appointment/{id}")
    public String shiftAppointment(@PathVariable(value = "id") Long id,
                                    @RequestBody Appointment new_appointment) {
        this.appointmentService.updateAppointment(id, new_appointment);
        return "all changes about appointment have been saved";
    }


    @DeleteMapping("appointment/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("appointment/pdf/generate/{id}")
    public String exportAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.exportAppointmentInfo(id);
        return "Printed successfully";
    }


    @GetMapping("appointment/remind/{id}")
    public String remindAppointment(@PathVariable(value = "id") Long id, @RequestParam String remind_option) {
        this.appointmentService.remindAppointment(id, remind_option);
        return "Remind appointment successfully!";
    }
}
