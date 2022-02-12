package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Appointment is a model for our main service, which is booking an appointment for a doctor.
 * @author Vinh Truong Canh Thanh
 */

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

    private Long doctor_id;
    private Long user_id;
    /**
     * Contructor
     * @author Vinh Truong Canh Thanh
     */

    public Appointment(String appointment_reason, String appointment_description, LocalTime start_time,
                       LocalTime end_time, LocalDate dateBooking, boolean booked) {
        this.appointment_reason = appointment_reason;
        this.appointment_description = appointment_description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dateBooking = dateBooking;
        this.booked = booked;
    }


    /**
     * Getter and Setter
     * @author Vinh Truong Canh Thanh
     */
    public Long getId() { return id; }

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

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * To String function
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointment_reason='" + appointment_reason + '\'' +
                ", appointment_description='" + appointment_description + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", dateBooking=" + dateBooking +
                ", booked=" + booked +
                '}';
    }
}
