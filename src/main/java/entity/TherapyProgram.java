package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "therapy_program")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TherapyProgram {

    @Id
    @Column(name = "program_id")
    private String programId;

    @Column(name = "program_name")
    private String programName;

    private String duration;

    private double fee;

    private String description;

    @ManyToMany(mappedBy = "programs")

    private List<Patient> patients;

    @OneToMany(mappedBy = "therapyProgram")
    private List<TherapySession> therapySessions;
}