package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;

import java.util.List;

@Entity
@Table(name = "patient")

public class Patient {

    @Id
    @Column(name = "patient_id")
    private String patientId;

    private String name;
    private String email;
    private String phone;
    private String gender;
    @Column(name = "medical_history")
    private String medicalHistory;

    @ManyToMany
    @JoinTable(name = "patient_program",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> programs;

    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    public Patient() {
    }

    public Patient(String patientId,
                   String name,
                   String email,
                   String phone,
                   String gender,
                   String medicalHistory) {

        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<TherapyProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(List<TherapyProgram> programs) {
        this.programs = programs;
    }

    public List<TherapySession> getTherapySessions() {
        return therapySessions;
    }

    public void setTherapySessions(List<TherapySession> therapySessions) {
        this.therapySessions = therapySessions;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}