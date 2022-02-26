package com.example.seraphine.repository;

import com.example.seraphine.model.Doctor;
import com.example.seraphine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.seraphine.model.Appointment;

import java.util.List;

/**
 * Repository interface to access from data and model, also create entity for appointment
 * @author Vinh Truong Canh Thanh, Linh Ngo Phuc
 */

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {
    @Query(value = "select * from Appointment where user_id = ?1", nativeQuery = true)
    List<Appointment> findByUserId(Long userId);

    @Query(value = "select * from Appointment where doctor_id = ?1", nativeQuery = true)
    List<Appointment> findByDoctorId(Long doctorId);
}
