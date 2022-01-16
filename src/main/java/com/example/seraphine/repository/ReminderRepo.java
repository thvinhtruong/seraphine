package com.example.seraphine.repository;

import com.example.seraphine.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Integer> {
}
