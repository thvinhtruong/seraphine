package com.example.seraphine.service;

import com.example.seraphine.model.Doctor;
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
    public Optional<Doctor> getDoctorById(int id) {
        return doctorRepo.findById(id);
    }
    @Override
    public Doctor updateDoctor(int id, Doctor newdoctor) {
        return (Doctor) this.doctorRepo.findById(id).map(doctor ->{
            doctor.setFirstName(newdoctor.getFirstName());
            doctor.setLastName(newdoctor.getLastName());
            doctor.setGender(newdoctor.getGender());
            doctor.setAddress(newdoctor.getAddress());
            doctor.setSpecialization(newdoctor.getSpecialization());
            return null;
        }).orElseGet(() -> {
            newdoctor.setId(id);
            return this.doctorRepo.save(newdoctor);
        });
    }
    @Override
    public void deleteDoctor(int id) {
        doctorRepo.delete(doctorRepo.getById(id));
    }

}
