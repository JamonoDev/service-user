package com.jamonodev.service_user.mapping;

import com.jamonodev.service_user.dto.RoleDTO;
import com.jamonodev.service_user.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Mapper une entité Role vers un RoleDTO
    RoleDTO toRoleDTO(Role role);

    // Mapper une entité Role vers un RoleDTO
    Role fromRoleDTO(RoleDTO roleDTO);
}
