package com.example.seraphine.service;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.model.QueryBuilder;
import com.example.seraphine.model.SearchCriteria;
import com.example.seraphine.model.SearchOperation;
import com.example.seraphine.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<Doctor> findDoctorWithCriteria(String issues, int distance_to_user) {
        String issue_criteria = "issue_covered";
        String distance_criteria = "distance_to_user";
        QueryBuilder newQuery = new QueryBuilder<Doctor>();
        newQuery.add(new SearchCriteria(issue_criteria, issues, SearchOperation.MATCH_END));
        newQuery.add(new SearchCriteria(distance_criteria, distance_to_user, SearchOperation.LESS_THAN_EQUAL));

        return doctorRepo.findAll(newQuery);
    }

}
