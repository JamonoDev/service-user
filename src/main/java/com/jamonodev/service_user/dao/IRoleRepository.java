package com.jamonodev.service_user.dao;

import com.jamonodev.service_user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    // Trouver un rôle par son nom
    Optional<Role> findByName(String name);
}
