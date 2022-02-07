package com.example.seraphine.model;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/** Email sender with scheduled or non-scheduled email sending method
 * @author Tri Nguyen Minh
 */
@Service
@AllArgsConstructor
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    private LocalDateTime convertToDate(LocalDate localDate, LocalTime localTime){
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Calculate the time to send the reminder email based on the appointment time and the appointment option. 
     * @param option
     * @param appointment
     * @return
     */
    public LocalDateTime setupDateToSend(String option, Appointment appointment){
        LocalTime time_send = null; //HH-mm
        LocalDate date_send = null; //yyyy-MM-dd

        if (option.equals("TENMINUTES")){
            time_send = appointment.getStart_time().minusMinutes(10);
            date_send = appointment.getDateBooking();
        }
        else if (option.equals("ONEHOUR")) {
            time_send = appointment.getStart_time().minusHours(1);
            date_send = appointment.getDateBooking();
        }
        else if (option.equals("THREEDAYS")) {
            time_send = appointment.getStart_time();
            date_send = appointment.getDateBooking().minusDays(3);
        }
        else if (option.equals("ONEWEEK")) {
            time_send = appointment.getStart_time();
            date_send = appointment.getDateBooking().minusDays(7);
        }
        return convertToDate(date_send, time_send);
    }

    /**
     * Send an email immediately to the user's email
     * @param recipient_mail
     * @param subject
     * @param body
     */
    public void sendEmail(String recipient_mail,
                                 String subject,
                                 String body){
        System.out.println("Prepare to send message...");
        SimpleMailMessage msg = new SimpleMailMessage();
        //Setting up mail attributes
        msg.setFrom("seraphine.webmail@gmail.com");
        msg.setTo(recipient_mail);
        msg.setText(body);
        msg.setSubject(subject);
        //Send the mail
        mailSender.send(msg);
        //Prompting the message
        System.out.println("Mail sent successfully!");
    }
    /**
     * Schedule to email the user based on the start time of the appointment
     * @param recipient_mail
     * @param appointment
     * @param option
     */
    public void sendScheduledMail(String recipient_mail, Appointment appointment, String option){
        String subject = "Reminder from Seraphine team";
        String body = "You have an appointment on " + appointment.getDateBooking()
                + " from " + appointment.getStart_time() + " to " + appointment.getEnd_time();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Prepare to send message...");
                    SimpleMailMessage msg = new SimpleMailMessage();
                    //Setting up mail attributes
                    msg.setFrom("seraphine.webmail@gmail.com");
                    msg.setTo(recipient_mail);
                    msg.setText(body);
                    msg.setSubject(subject);
                    //Send the mail
                    mailSender.send(msg);
                    //Prompting the message
                    System.out.println("Mail sent successfully!");
                    System.out.println(LocalDateTime.now());
                } catch (Exception e) {
                    System.out.println("Failed to send appointment reminder email!");
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, Date.from(this.setupDateToSend(option, appointment)
                            .atZone(ZoneId.systemDefault())
                            .toInstant()));
    }
}
