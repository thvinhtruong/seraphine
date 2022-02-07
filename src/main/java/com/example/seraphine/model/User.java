package com.example.seraphine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

/**
 * Main object for the project
 * @author Loc Bui Nhien, Vinh Truong Canh Thanh
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
    private String insuranceType;
    private String insuranceName;
    private String userRole;

    @OneToMany(targetEntity = Appointment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="appointments",referencedColumnName = "id")
    private Set<Appointment> myAppointment = new HashSet<>();

    private Boolean locked = false;
    private Boolean enabled = false;
    private String resetPasswordToken;


    /**
     * The method is used to create a new User object.
     * <p>
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param insuranceType The insurance type of the user.
     * @param insuranceName The insurance name of the user.
    */
    public User(String firstName, String lastName, String email, String username, String password, String dateOfBirth, String insuranceType, String insuranceName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
    }

    /**
     * Get user's ID.
     * @return user's ID
     */
    public Long getId(){
        return id;
    }

    /**
     * Set user's ID.
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get user's first name.
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get user's last name.
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get user's email.
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get user's username.
     * @return user's username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Get user's password.
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get user's date of birth.
     * @return user's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Get user's insurance type.
     * @return user's insurance type
     */
    public String getInsuranceType() {
        return insuranceType;
    }

    /**
     * Get user's insurance name.
     * @return user's insurance name
     */
    public String getInsuranceName() {
        return insuranceName;
    }

    /**
     * Set user's first name.
     * @param firstName user's first name
     */
    public void setFistName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set user's last name.
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set user's email.
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set user's username.
     * @param username user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set user's password.
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set user's date of birth.
     * @param dateOfBirth user's date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Set user's insurance type.
     * @param insuranceType user's insurance type
     */
    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    /**
     * Set user's insurance name.
     * @param insuranceName user's insurance name
     */
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    /**
     * Get user's appointment.
     * @return user's appointment
     */
    public Set<Appointment> getMyAppointment() {
        return myAppointment;
    }

    /**
     * Get user's locked status.
     * @return is the user locked
     */
    public Boolean getLocked() {
        return locked;
    }
    

    /**
     * Set user's locked status.
     * @param locked is the user locked
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }


    /**
     * Get user's enabled status.
     * @return is the user enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set user's enabled status.
     * @param enabled is the user enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Ger user's reset password token.
     * @return user's reset password token
     */
    public String getResetPasswordToken(){
        return resetPasswordToken;
    }

    /**
     * Set user's appointment.
     * @param myAppointment user's appointment
     */
    public void setMyAppointment(Set<Appointment> myAppointment) {
        this.myAppointment = myAppointment;
    }

    /**
     * Set user's reset password token.
     * @param resetPasswordToken user's reset password token
     */
    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    /**
     * Get user's role.
     * @return user's role.
     */
    public String getUserRole() {
        return userRole;
    }
    /**
     * Set user's role.
     * @param userRole user's role
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getUserRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", myAppointment=" + myAppointment +
                ", userRole=" + userRole +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", resetPasswordToken='" + resetPasswordToken + '\'' +
                '}';
    }
}
