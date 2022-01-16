package com.example.seraphine.service;

import com.example.seraphine.model.Reminder;
import com.example.seraphine.model.User;
import java.util.Optional;

import java.util.List;

public interface ReminderService {
    void saveReminder(Reminder reminder);
    List<Reminder> getAllReminders();
    Optional<Reminder> getReminderById(int id);
    void setReminderForUser(String reminder_option, User user);
}
