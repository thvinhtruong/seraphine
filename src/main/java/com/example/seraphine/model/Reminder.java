package com.example.seraphine.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reminder")
@NoArgsConstructor

public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String reminder_option;
    private LocalDateTime endDate;

    public Reminder(String reminder_option, LocalDateTime endDate) {
        this.reminder_option = reminder_option;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReminder_option() {
        return reminder_option;
    }

    public void setReminder_option(String reminder_option) {
        this.reminder_option = reminder_option;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reminder)) return false;
        Reminder reminder = (Reminder) o;
        return getId() == reminder.getId() && Objects.equals(getReminder_option(), reminder.getReminder_option()) && Objects.equals(getEndDate(), reminder.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReminder_option(), getEndDate());
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", reminder_option='" + reminder_option + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}
