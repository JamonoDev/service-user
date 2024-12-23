package com.jamonodev.service_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;  // Nom du rôle, par exemple "GestionnaireFlotte", "Caissière", etc.

}
