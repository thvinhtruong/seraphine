package com.example.seraphine.controller;

import com.example.seraphine.model.Appointment;
import com.example.seraphine.service.AppointmentService;;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("api/v1/appointment/post")
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "new appointment has been successfully made";
    }

    @GetMapping("api/v1/appointment/all")
    public List<Appointment> showAllAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    @GetMapping("api/v1/appointment/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable(value = "id") Long id) {
        Optional<Appointment> appointment = this.appointmentService.getAppointmentById(id);
        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping("api/v1/appointment/{id}")
    public String shiftAppointment(@PathVariable(value = "id") Long id, @RequestBody Appointment new_appointment) {
        this.appointmentService.updateAppointment(id, new_appointment);
        return "all changes about appointment have been saved";
    }

    @DeleteMapping("api/v1/appointment/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(value = "id") Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
