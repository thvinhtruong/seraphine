package com.example.seraphine.service;

import com.example.seraphine.model.*;
import com.example.seraphine.repository.DoctorRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    @Override
    public void saveDoctor(Doctor doctor){
        doctorRepo.save(doctor);
    }
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }
    @Override
    public Optional<Doctor> getDoctorById(long id) {
        return doctorRepo.findById(id);
    }
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
    @Override
    public void deleteDoctor(long id) {
        doctorRepo.deleteById(id);
    }

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

    @Override
    public void addAppointmentToDoctor(Long doctor_id, Appointment appointment) {

    }

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

    @Override
    public List<Appointment> showAvailableAppointments(Long doctor_id) {
        Optional<Doctor> doctor_obj = this.doctorRepo.findById(doctor_id);
        if (doctor_obj.isEmpty()) {
            System.out.println("doctor not found");
        }
        Doctor doctor = doctor_obj.get();
        return doctor.getAppointments();
    }

}
