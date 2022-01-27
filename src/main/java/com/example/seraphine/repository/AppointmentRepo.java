package com.example.seraphine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.seraphine.model.Appointment;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>, PagingAndSortingRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long user_id);
    List<Appointment> findByDoctorId(Long doctor_id);

}
