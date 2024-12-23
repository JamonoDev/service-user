package com.jamonodev.service_user.controller;

import com.jamonodev.service_user.dto.RoleDTO;
import com.jamonodev.service_user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Récupérer tous les rôles
    @GetMapping
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    // Récupérer un rôle par son ID
    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    // Récupérer un rôle par son nom
    @GetMapping("/name/{roleName}")
    public RoleDTO getRoleByName(@PathVariable String roleName) {
        return roleService.getRoleByName(roleName);
    }

    // Créer un rôle
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }
}
