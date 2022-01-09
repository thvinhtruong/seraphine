package com.example.seraphine.model;

import java.time.LocalDateTime;

public class Appointment {
    private String doctor_id;
    private String doctor_name;
    private String appointment_id;
    private LocalDateTime dateTime; // yyyy-MM-dd-HH-mm-ss-ns
    private String reminder_time;

    public Appointment(String doctor_id, String doctor_name, String appointment_id, LocalDateTime dateTime, String reminder_time) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.appointment_id = appointment_id;
        this.dateTime = dateTime;
        this.reminder_time = reminder_time;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReminder_time() {
        return reminder_time;
    }

    public void setReminder_time(String reminder_time) {
        this.reminder_time = reminder_time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor_id='" + doctor_id + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", appointment_id='" + appointment_id + '\'' +
                ", dateTime=" + dateTime +
                ", reminder_time='" + reminder_time + '\'' +
                '}';
    }
}
