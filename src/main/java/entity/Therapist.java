package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.util.List;

@Entity
@Table(name = "therapist")

public class Therapist {

    @Id
    @Column(name = "therapist_id")
    private String therapistId;

    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String availability;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySessions;

    public Therapist() {
    }

    public Therapist(String therapistId,
                     String name,
                     String email,
                     String phone,
                     String specialization,
                     String availability) {

        this.therapistId = therapistId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getTherapistId() {
        return therapistId;
    }

    public void setTherapistId(String therapistId) {
        this.therapistId = therapistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<TherapySession> getTherapySessions() {
        return therapySessions;
    }

    public void setTherapySessions(List<TherapySession> therapySessions) {
        this.therapySessions = therapySessions;
    }
}