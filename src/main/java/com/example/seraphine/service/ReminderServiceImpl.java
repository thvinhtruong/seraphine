package com.example.seraphine.service;

import com.example.seraphine.model.Reminder;
import com.example.seraphine.repository.ReminderRepo;
import com.example.seraphine.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private ReminderRepo reminderRepo;

    @Override
    public void saveReminder(Reminder reminder) {
        this.reminderRepo.save(reminder);
    }

    @Override
    public List<Reminder> getAllReminders() {
        return this.reminderRepo.findAll();
    }

    @Override
    public Optional<Reminder> getReminderById(int id) {
        return this.reminderRepo.findById(id);
    }

    @Override
    public void setReminderForUser(String reminder_option, User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateSchedule = calendar.getTime();
        long period = 24 * 60 * 60 * 1000;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Send email to user at 9 am");
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, dateSchedule, period);


    }

}
