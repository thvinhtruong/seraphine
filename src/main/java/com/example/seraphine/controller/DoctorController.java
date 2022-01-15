package com.example.seraphine.controller;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("api/v1/doctor/post")
    public String createDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "new doctor is successfully added";
    }

    @GetMapping("api/v1/doctor/all")
    public List<Doctor> showAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("api/v1/doctor/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctor(@PathVariable(value = "id") int id) {
        Optional<Doctor> doctor = this.doctorService.getDoctorById(id);
        return ResponseEntity.ok().body(doctor);
    }

    @PutMapping("api/v1/doctor/{id}")
    public String updateDoctor(@PathVariable(value = "id") int id, @RequestBody Doctor newDoctor) {
        this.doctorService.updateDoctor(id, newDoctor);
        return "all changes about doctor have been saved";
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable(value = "id") int id) {
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
