package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    @Id
    private String userId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String role;
}