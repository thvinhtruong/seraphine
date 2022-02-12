package com.example.seraphine.controller;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.service.DoctorService;
import lombok.AllArgsConstructor;
import com.example.seraphine.model.Appointment;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/**
 * The controller for the doctor operations.
 * @author Vinh Truong Canh Thanh
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/doctor")
@CrossOrigin(origins = "http://localhost:3000/search")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    /**
     * Add a doctor to database
     * @param doctor Doctor
     * @return String
     */

    @PostMapping("/post")
    public String createDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "new doctor is successfully added";
    }

    /**
     * List all doctors
     * @return list of all doctor
     */

    @GetMapping("/all")
    public List<Doctor> showAllDoctors() {
        return doctorService.getAllDoctors();
    }

    /**
     * Get a doctor based on id
     * @param doctor_id Long
     * @return doctor JSON data based on id
     */

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctor(@PathVariable(value = "id") Long doctor_id) {
        Optional<Doctor> doctor = this.doctorService.getDoctorById(doctor_id);
        return ResponseEntity.ok().body(doctor);
    }

    /**
     * Edit doctor information
     * @param doctor_id Long
     * @param newDoctor Doctor
     * @return String
     */

    @PutMapping("/{id}")
    public String updateDoctor(@PathVariable(value = "id") Long doctor_id, @RequestBody Doctor newDoctor) {
        this.doctorService.updateDoctor(doctor_id, newDoctor);
        return "all changes about doctor have been saved";
    }

    /**
     * Delete a doctor based on id
     * @param doctor_id long
     * @return status for empty doctor
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable(value = "id") Long doctor_id) {
        this.doctorService.deleteDoctor(doctor_id);
        return ResponseEntity.ok().build();
    }

    /**
     * Search query doctor in database
     * @param issue_covered String
     * @param address String
     * @param distance_to_user int
     * @return list of doctor satisfied the condition
     */

    @GetMapping(path = "/search/query")
    public List<Doctor> displayAllDoctorsWithQuery(@RequestParam String issue_covered,
                                                   @RequestParam String address,
                                                   @RequestParam int distance_to_user) {
        return this.doctorService.findDoctorWithCriteria(issue_covered, address, distance_to_user);
    }

    /**
     * Show all available appointment for doctor based on id
     * @param doctor_id Long
     * @return List of appointment
     */

    @GetMapping("/appointment/{id}/all")
    public List<Appointment> displayAllAppointments(@PathVariable(value = "id") Long doctor_id) {
        return this.doctorService.showAvailableAppointments(doctor_id);
    }
}
