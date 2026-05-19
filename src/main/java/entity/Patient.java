package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "patient")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Patient {

    @Id
    private String patientId;

    private String name;

    private String email;

    private String phone;

    private String gender;

    private String medicalHistory;

    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(

            name = "patient_program",

            joinColumns =
            @JoinColumn(name = "patient_id"),

            inverseJoinColumns =
            @JoinColumn(name = "program_id")

    )

    private List<TherapyProgram> programs;
}