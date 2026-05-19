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
    private String programId;

    private String programName;

    private String duration;

    private double fee;

    private String description;

    @ManyToMany(mappedBy = "programs")

    private List<Patient> patients;
}