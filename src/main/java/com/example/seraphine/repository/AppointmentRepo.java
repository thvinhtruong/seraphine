package com.example.seraphine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.seraphine.model.Appointment;
import java.util.List;

/**
 * Repository interface to access from data and model, also create entity for appointment
 * @author Vinh Truong Canh Thanh
 */

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>, PagingAndSortingRepository<Appointment, Long> {
}
