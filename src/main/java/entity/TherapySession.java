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
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "session_date")
    private java.sql.Date sessionDate;

    @Column(name = "session_time")
    private java.sql.Time sessionTime;

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