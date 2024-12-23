package com.jamonodev.service_user.service;

import com.jamonodev.service_user.dto.RoleDTO;
import com.jamonodev.service_user.entities.Role;
import com.jamonodev.service_user.mapping.RoleMapper;
import com.jamonodev.service_user.dao.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(IRoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    // Récupérer tous les rôles
    @Transactional(readOnly = true)
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toRoleDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un rôle par son nom
    @Transactional(readOnly = true)
    public RoleDTO getRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.toRoleDTO(role);
    }

    // Récupérer un rôle par son ID
    @Transactional(readOnly = true)
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.toRoleDTO(role);
    }

    // Créer un rôle
    @Transactional
    public RoleDTO createRole(RoleDTO roleDTO) {
        return roleMapper.toRoleDTO(roleRepository.save(roleMapper.fromRoleDTO(roleDTO)));
    }
}
