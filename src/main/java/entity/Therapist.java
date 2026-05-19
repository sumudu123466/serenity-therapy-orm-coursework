package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "therapist")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Therapist {

    @Id
    private String therapistId;

    private String name;

    private String email;

    private String phone;

    private String specialization;

    private String availability;

    @OneToMany(
            mappedBy = "therapist",
            cascade = CascadeType.ALL
    )

    private List<TherapySession> sessions;
}