package com.example.seraphine.controller;

import com.example.seraphine.model.*;
import com.example.seraphine.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


import javax.mail.MessagingException;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/post")
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "new appointment has been successfully made";
    }

    @PostMapping("/user/{userId}/book")
    public ResponseEntity<Appointment> bookAppointment(@PathVariable(value = "id") Long id, @RequestBody Appointment new_appointment) {
        return ResponseEntity.ok(this.appointmentService.bookAppointment(id, new_appointment));
    }

    @PostMapping("/doctor/{doctorId}/add")
    public ResponseEntity<Appointment> addAppointmentToDoctor(@PathVariable(value = "id") Long id, @RequestBody Appointment new_appointment) {
        return ResponseEntity.ok(this.appointmentService.addDoctorAppointment(id, new_appointment));
    }

    @GetMapping("/all")
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable(value = "id") Long id) {
        Optional<Appointment> appointment = this.appointmentService.getAppointmentById(id);
        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping("/{id}")
    public String shiftAppointment(@PathVariable(value = "id") Long id, @RequestBody Appointment new_appointment) {
        this.appointmentService.updateAppointment(id, new_appointment);
        return "all changes about appointment have been saved";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pdf/generate/{id}")
    public String exportAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.exportAppointmentInfo(id);
        return "Printed successfully";
    }

    @GetMapping("/remind/{id}")
    public String remindAppointment(@PathVariable(value = "id") Long id, @RequestParam String remind_option) {
        this.appointmentService.remindAppointment(id, remind_option);
        return "Remind appointment successfully!";
    }
}
