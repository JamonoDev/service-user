package com.jamonodev.service_user.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Le nom du rôle (ex. "GestionnaireFlotte", "Caissière", etc.)

    // Vous pouvez ajouter des méthodes ou d'autres propriétés spécifiques à la gestion des rôles si nécessaire.
}
