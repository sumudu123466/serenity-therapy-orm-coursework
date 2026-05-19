package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "therapy_session")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TherapySession {

    @Id
    private String sessionId;

    private String sessionDate;

    private String sessionTime;

    @ManyToOne

    @JoinColumn(name = "patient_id")

    private Patient patient;

    @ManyToOne

    @JoinColumn(name = "therapist_id")

    private Therapist therapist;

    @ManyToOne

    @JoinColumn(name = "program_id")

    private TherapyProgram therapyProgram;
}