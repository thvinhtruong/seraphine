package com.example.seraphine.service;

import com.example.seraphine.model.*;
import com.example.seraphine.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Operation / logic for doctor
 * @author Vinh Truong Canh Thanh
 */

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;

    /**
     * Save doctor to database
     * @param doctor Doctor
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void saveDoctor(Doctor doctor){
        doctorRepo.save(doctor);
    }

    /**
     * Display all doctor
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    /**
     * Get a doctor based on id
     * @param id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public Optional<Doctor> getDoctorById(long id) {
        return doctorRepo.findById(id);
    }

    /**
     * Update information for a doctor
     * @param id Long
     * @param newdoctor Doctor
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void updateDoctor(long id, Doctor newdoctor) {
        this.doctorRepo.findById(id).map(doctor -> {
            doctor.setFirstName(newdoctor.getFirstName());
            doctor.setLastName(newdoctor.getLastName());
            doctor.setGender(newdoctor.getGender());
            doctor.setEmails(newdoctor.getEmails());
            doctor.setAddress(newdoctor.getAddress());
            doctor.setSpecialization(newdoctor.getSpecialization());
            doctor.setDistance_to_user(newdoctor.getDistance_to_user());
            doctor.setIssue_covered(newdoctor.getIssue_covered());
            return null;
        }).orElseGet(() -> {
            newdoctor.setId(id);
            return this.doctorRepo.save(newdoctor);
        });
    }

    /**
     * Delete doctor from database
     * @param id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void deleteDoctor(long id) {
        doctorRepo.deleteById(id);
    }

    /**
     * calculate distance between doctor to user
     * @param address String
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public void calculateDistanceToUser(String address) {
        Location location_criteria = new Location(address); // address of user
        String address_criteria = "address";
        QueryBuilder<Doctor> newQuery = new QueryBuilder<Doctor>();
        newQuery.add(new SearchCriteria(address_criteria, address, SearchOperation.MATCH));
        List<Doctor> accepted_doctor = doctorRepo.findAll(newQuery);

        // add try catch later
        for (Doctor doctor : accepted_doctor) {
            Location doctor_location = new Location(doctor.getAddress());
            double distance = doctor_location.distanceCalculator(location_criteria.locationConverter());
            doctor.setDistance_to_user(distance);
        }
    }

    /**
     * Find all doctors that satisfied the condition (query search for doctor)
     * @param issues String
     * @param address String
     * @param distance_to_user int
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public List<Doctor> findDoctorWithCriteria(String issues, String address, int distance_to_user) {
        this.calculateDistanceToUser(address);
        String issue_criteria = "issue_covered";
        String distance_criteria = "distance_to_user";
        String address_criteria = "address";
        QueryBuilder<Doctor> newQuery = new QueryBuilder<Doctor>();
        newQuery.add(new SearchCriteria(issue_criteria, issues, SearchOperation.MATCH));
        newQuery.add(new SearchCriteria(address_criteria, address, SearchOperation.MATCH));
        newQuery.add(new SearchCriteria(distance_criteria, distance_to_user, SearchOperation.LESS_THAN_EQUAL));
        return doctorRepo.findAll(newQuery);
    }

    /**
     * Show all doctor appointment that available for user
     * @param doctor_id Long
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public List<Appointment> showAvailableAppointments(Long doctor_id) {
        List<Appointment> results = new ArrayList<Appointment>();
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();
        for (int i = 0; i < doctor.getAppointments().size(); i++) {
            if (!doctor.getAppointments().get(i).isBooked()) {
                results.add(doctor.getAppointments().get(i));
            }
        }
        return results;
    }

}
