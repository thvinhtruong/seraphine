package com.example.seraphine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seraphine.repository.AppointmentRepo;
import com.example.seraphine.model.EmailSender;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.example.seraphine.model.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public void saveAppointment(Appointment appointment) {
        this.appointmentRepo.save(appointment);
    }
    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepo.findAll();
    }
    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return this.appointmentRepo.findById(id);
    }
    @Override
    public Appointment updateAppointment(Long id, Appointment new_appointment) {
        return (Appointment) this.appointmentRepo.findById(id).map(appointment -> {
            appointment.setAppointment_reason(new_appointment.getAppointment_reason());
            appointment.setAppointment_description(new_appointment.getAppointment_description());
            appointment.setStart_time(new_appointment.getStart_time());
            appointment.setEnd_time(new_appointment.getEnd_time());
            appointment.setDateBooking(new_appointment.getDateBooking());
            appointment.setLocationZipCode(new_appointment.getLocationZipCode());
            return null;
        }).orElseGet(() -> {
            new_appointment.setId(id);
            return this.appointmentRepo.save(new_appointment);
        });
    }
    @Override
    public void deleteAppointment(Long id) {
        this.appointmentRepo.delete(appointmentRepo.getById(id));
    }

    @Override
    public void reminderUser(String emails) {
        // duration
        int duration = this.appointmentRepo.convertToTimeDuration();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateSchedule = calendar.getTime();
        long period = duration;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                this.sendEmailToUser(emails);
            }
            private void sendEmailToUser(String emails) {
                EmailSender sender = new EmailSender();
                String title = "Appointment Reminder: Remember your important date with us!";
                String body = "You have appointment on the next coming days from Seraphine Service Team!";
                sender.sendEmail(emails, title, body);
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, dateSchedule, period);


    }
}


