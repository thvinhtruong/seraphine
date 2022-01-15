package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;

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
    private String timestamp;
    private String dateBooking;
    private String location;
    private int zipCode;
    private int reminder_time;

    public Appointment(int doctor_id, String appointment_reason, String appointment_description, String timestamp, String dateBooking,
                       String location, int zipCode, int reminder_time) {
        this.doctor_id = doctor_id;
        this.appointment_reason = appointment_reason;
        this.appointment_description = appointment_description;
        this.timestamp = timestamp;
        this.dateBooking = dateBooking;
        this.location = location;
        this.zipCode = zipCode;
        this.reminder_time = reminder_time;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getReminder_time() {
        return reminder_time;
    }

    public void setReminder_time(int reminder_time) {
        this.reminder_time = reminder_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return getDoctor_id() == that.getDoctor_id() && getZipCode() == that.getZipCode()
                && getReminder_time() == that.getReminder_time() &&
                Objects.equals(getId(), that.getId()) && Objects.equals(getAppointment_reason(),
                that.getAppointment_reason()) && Objects.equals(getAppointment_description(),
                that.getAppointment_description()) && Objects.equals(getTimestamp(),
                that.getTimestamp()) && Objects.equals(getDateBooking(), that.getDateBooking()) &&
                Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDoctor_id(), getAppointment_reason(), getAppointment_description(), getTimestamp(),
                getDateBooking(), getLocation(), getZipCode(), getReminder_time());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", appointment_reason='" + appointment_reason + '\'' +
                ", appointment_description='" + appointment_description + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", dateBooking='" + dateBooking + '\'' +
                ", location='" + location + '\'' +
                ", zipCode=" + zipCode +
                ", reminder_time=" + reminder_time +
                '}';
    }
}
