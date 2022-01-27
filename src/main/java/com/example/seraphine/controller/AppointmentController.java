package com.example.seraphine.controller;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.service.AppointmentService;
import com.example.seraphine.model.User;
import com.example.seraphine.model.Doctor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Appointment>>getUserAppointment(@RequestBody User user, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(this.appointmentService.showAllUsersAppointments(id));

    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Appointment>>getDoctorAppointment(@RequestBody Doctor doctor, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(this.appointmentService.showAllDoctorsAppointments(id));
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
}
