package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Appointment")
@NoArgsConstructor

public class Appointment {

    @Id
    @SequenceGenerator(name = "BOOKING_SEQ",sequenceName = "booking_seq",
            initialValue = 123, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ")

    private Long id;
    private String appointment_reason;
    private String appointment_description;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH-mm")
    private LocalTime start_time;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH-mm")
    private LocalTime end_time;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dateBooking;

    private boolean booked = false;

    private Long userId;
    private Long doctorId;


    public Appointment(String appointment_reason, String appointment_description, LocalTime start_time,
                       LocalTime end_time, LocalDate dateBooking, boolean booked) {
        this.appointment_reason = appointment_reason;
        this.appointment_description = appointment_description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dateBooking = dateBooking;
        this.booked = booked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public boolean isBooked() {
        return booked;
    }

    public void setStatus(boolean booked) {
        this.booked = booked;
    }

}
