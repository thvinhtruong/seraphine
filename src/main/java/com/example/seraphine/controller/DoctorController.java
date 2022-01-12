package com.example.seraphine.controller;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;


@RestController
// @RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor/post")
    public String post(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "new doctor is successfully added";
    }

    @GetMapping("/doctor/all")
    public List<Doctor> list() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctor(@PathVariable(value = "id") int id) {
        Optional<Doctor> doctor = this.doctorService.getDoctorById(id);
        return ResponseEntity.ok().body(doctor);
    }

    @PutMapping("/doctor/{id}")
    public Doctor updateDoctor(@PathVariable(value = "id") int id, @RequestBody Doctor newDoctor) {
        return this.doctorService.updateDoctor(id, newDoctor);
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable(value = "id") int id) {
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
