package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.*;

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
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalDate dateBooking;
    private int locationZipCode;

    public Appointment(int doctor_id, String appointment_reason, String appointment_description, LocalTime start_time, LocalTime end_time, LocalDate dateBooking, int locationZipCode) {
        this.doctor_id = doctor_id;
        this.appointment_reason = appointment_reason;
        this.appointment_description = appointment_description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dateBooking = dateBooking;
        this.locationZipCode = locationZipCode;
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

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }

    public int getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(int locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", appointment_reason='" + appointment_reason + '\'' +
                ", appointment_description='" + appointment_description + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", dateBooking=" + dateBooking +
                ", locationZipCode=" + locationZipCode +
                '}';
    }
}
