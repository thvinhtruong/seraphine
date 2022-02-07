package com.example.seraphine.repository;

import com.example.seraphine.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * Repository storing all the doctor's information
 * @author Vinh Truong Canh Thanh
 */
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
}
