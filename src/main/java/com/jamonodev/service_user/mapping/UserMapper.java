package com.jamonodev.service_user.mapping;

import com.jamonodev.service_user.dto.UserDTO;
import com.jamonodev.service_user.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Conversion de UserEntity vers UserDTO

    //@Mapping(source = "role.id", target = "roleId")
    //@Mapping(source = "role", target = "roleName")
    UserDTO toUserDTO(UserEntity userEntity);

    // Conversion de UserDTO vers UserEntity
    //@Mapping(source = "roleId", target = "role.id")
    UserEntity fromUserDTO(UserDTO userDTO);
}
