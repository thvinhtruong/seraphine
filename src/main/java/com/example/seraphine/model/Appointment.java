package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "Appointment")
@NoArgsConstructor

public class Appointment {

    @Id
    @SequenceGenerator(name = "BOOKING_SEQ",sequenceName = "booking_seq",
            initialValue = 123, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ")

    private Long id;
    private int doctor_id;
    private String appointment_reason;
    private String appointment_description;
    private String start_time;
    private String end_time;
    private String dateBooking;
    private int locationZipCode;
    private String reminder_option;

    public Appointment(String appointment_reason, String appointment_description, String start_time, String end_time, String dateBooking, int locationZipCode, String reminder_option) {
        this.appointment_reason = appointment_reason;
        this.appointment_description = appointment_description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dateBooking = dateBooking;
        this.locationZipCode = locationZipCode;
        this.reminder_option = reminder_option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getAppointment_reason() {
        return appointment_reason;
    }

    public void setAppointment_reason(String appointment_reason) {
        this.appointment_reason = appointment_reason;
    }

    public String getAppointment_description() {
        return appointment_description;
    }

    public void setAppointment_description(String appointment_description) {
        this.appointment_description = appointment_description;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public int getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(int locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    public String getReminder_option() {
        return reminder_option;
    }

    public void setReminder_option(String reminder_option) {
        this.reminder_option = reminder_option;
    }

    public int convertToTimeDuration() {
        if (this.reminder_option == "10 minutes") {
            return 1000 * 60 * 10;
        } else if (this.reminder_option == "1 hour") {
            return 1000 * 60 * 60;
        } else if (this.reminder_option == "3 days") {
            return 1000 * 60 * 60 * 24 * 3;
        } else if (this.reminder_option == "1 week") {
            return 1000 * 60 * 60 * 24 * 7;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", appointment_reason='" + appointment_reason + '\'' +
                ", appointment_description='" + appointment_description + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", dateBooking='" + dateBooking + '\'' +
                ", locationZipCode=" + locationZipCode +
                ", reminder_option='" + reminder_option + '\'' +
                '}';
    }
}
