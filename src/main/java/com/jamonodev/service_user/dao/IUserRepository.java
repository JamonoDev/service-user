package com.jamonodev.service_user.dao;

import com.jamonodev.service_user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    // Trouver un utilisateur par son email
    Optional<UserEntity> findByEmail(String email);

    // Trouver un utilisateur par son numéro (ex. numéro d'employé)
    Optional<UserEntity> findByNumber(Long number);

    // Trouver un utilisateur par son rôle
    List<UserEntity> findByRoleName(String roleName);

    // Récupérer uniquement les utilisateurs non archivés
    List<UserEntity> findByArchivedFalse();

    // Récupérer uniquement les utilisateurs archivés
    List<UserEntity> findByArchivedTrue();

}
