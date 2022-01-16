package com.example.seraphine.model;

import java.util.List;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name = "Doctor")
@NoArgsConstructor

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String emails;
    private String address;
    private String specialization;

    @OneToMany(targetEntity = Appointment.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="appointments",referencedColumnName = "id")
    List<Appointment> listAppointments;

    public Doctor(String firstName, String lastName, String gender, String emails, String address, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emails = emails;
        this.address = address;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public List<Appointment> getListAppointments() {
        return listAppointments;
    }

    public void setListAppointments(List<Appointment> listAppointments) {
        this.listAppointments = listAppointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", emails='" + emails + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
