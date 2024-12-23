package com.jamonodev.service_user.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Data  // Lombok : génère automatiquement getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Génère un constructeur sans argument
@AllArgsConstructor // Génère un constructeur avec tous les arguments
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String phone;
    private String gender;
    private Boolean archived;
    @Column(nullable = true)
    private String drivingLicense;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;  // Lien vers la classe Role

}
