package com.example.seraphine.controller;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.service.DoctorService;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
=======
import com.example.seraphine.model.Appointment;
>>>>>>> refs/remotes/origin/main
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/post")
    public String createDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "new doctor is successfully added";
    }

    @GetMapping("/all")
    public List<Doctor> showAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctor(@PathVariable(value = "id") Long id) {
        Optional<Doctor> doctor = this.doctorService.getDoctorById(id);
        return ResponseEntity.ok().body(doctor);
    }

    @PutMapping("/{id}")
    public String updateDoctor(@PathVariable(value = "id") Long id, @RequestBody Doctor newDoctor) {
        this.doctorService.updateDoctor(id, newDoctor);
        return "all changes about doctor have been saved";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable(value = "id") Long id) {
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/query")
    public List<Doctor> displayAllDoctorsWithQuery(@RequestParam String issue_covered,
                                                   @RequestParam String address,
                                                   @RequestParam int distance_to_user) {
        return this.doctorService.findDoctorWithCriteria(issue_covered, address, distance_to_user);
    }

    @GetMapping("/appointment/{id}/all")
    public List<Appointment> displayAllAppointments(@PathVariable(value = "id") Long id) {
        return this.doctorService.showAvailableAppointments(id);
    }
}
