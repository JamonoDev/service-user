package com.jamonodev.service_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private Long number;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String phone;
    private String gender;
    private Boolean archived;
    private String drivingLicense; // Facultatif, dépend du rôle de l'utilisateur
    private RoleDTO role; // Identifiant du rôle de l'utilisateur
   // private String roleName; // Nom du rôle de l'utilisateur, par exemple "GestionnaireFlotte"

}
