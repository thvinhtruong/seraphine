package com.example.seraphine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Doctor object, which is the second object for the web app
 * @author Vinh Truong Canh Thanh
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Doctor")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String emails;
    private String address;
    private String specialization;
    private double distance_to_user;
    private String issue_covered;

    @OneToMany(targetEntity = Appointment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="appointments", referencedColumnName = "doctor_id")
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Contructor
     * @author Vinh Truong Canh Thanh
     */

    public Doctor(String firstName, String lastName, String gender,
                  String emails, String address, String specialization,
                  double distance_to_user, String issue_covered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emails = emails;
        this.address = address;
        this.specialization = specialization;
        this.distance_to_user = distance_to_user;
        this.issue_covered = issue_covered;
    }

    /**
     * Get doctor id
     */

    public Long getId() {
        return id;
    }

    /**
     * Set corresponding doctor id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get doctor first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set doctor first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get doctor last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set doctor last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get doctor gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set doctor gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get doctor address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set doctor address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get doctor specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Set doctor specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Get doctor email
     */
    public String getEmails() {
        return emails;
    }

    /**
     * Set doctor email
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * Get doctor distance to user
     */
    public double getDistance_to_user() {
        return distance_to_user;
    }

    /**
     * Set doctor distance to user
     */
    public void setDistance_to_user(double distance_to_user) {
        this.distance_to_user = distance_to_user;
    }

    /**
     * Get doctor issue cover
     */
    public String getIssue_covered() {
        return issue_covered;
    }

    /**
     * Set doctor issue cover
     */
    public void setIssue_covered(String issue_covered) {
        this.issue_covered = issue_covered;
    }

    /**
     * get doctor list of appointment
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Set doctor list of appointment
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * To String function, format: JSON
     * @author Vinh Truong Canh Thanh
     */

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
                ", distance_to_user=" + distance_to_user +
                ", issue_covered='" + issue_covered + '\'' +
                '}';
    }
}
