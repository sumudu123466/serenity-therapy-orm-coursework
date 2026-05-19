package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Payment {

    @Id
    private String paymentId;

    private double amount;

    private String paymentDate;

    private String paymentMethod;

    @ManyToOne

    @JoinColumn(name = "patient_id")

    private Patient patient;
}