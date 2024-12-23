package com.jamonodev.service_user.service;

import com.jamonodev.service_user.dao.IRoleRepository;
import com.jamonodev.service_user.dto.RoleDTO;
import com.jamonodev.service_user.dto.UserDTO;
import com.jamonodev.service_user.entities.Role;
import com.jamonodev.service_user.entities.UserEntity;
import com.jamonodev.service_user.mapping.UserMapper;
import com.jamonodev.service_user.dao.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper, MessageSource messageSource, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    // Récupérer tous les utilisateurs non archivés
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        // Récupérer les utilisateurs non archivés
        List<UserEntity> activeUsers = userRepository.findAll();
        // Convertir les entités en DTO et les retourner
        return activeUsers.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }
    // Récupérer tous les utilisateurs non archivés
    @Transactional(readOnly = true)
    public List<UserDTO> findByRoleName(String roleName) {

        // Récupérer les utilisateurs non archivés
        List<UserEntity> activeUsers = userRepository.findByRoleName(StringUtils.capitalize(roleName));
        // Convertir les entités en DTO et les retourner
        return activeUsers.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }
    // Récupérer tous les utilisateurs non archivés
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() {
        // Récupérer les utilisateurs non archivés
        List<UserEntity> activeUsers = userRepository.findByArchivedFalse();
        // Convertir les entités en DTO et les retourner
        return activeUsers.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    // Récupérer tous les utilisateurs archivés
    @Transactional(readOnly = true)
    public List<UserDTO> getArchivedUsers() {
        // Récupérer les utilisateurs archivés
        List<UserEntity> archivedUsers = userRepository.findByArchivedTrue();
        // Convertir les entités en DTO et les retourner
        return archivedUsers.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un utilisateur par son email et le mapper en DTO
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{email}, Locale.getDefault())));
        return userMapper.toUserDTO(userEntity);
    }

    // Récupérer un utilisateur par son ID et le mapper en DTO
    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault())));
        return userMapper.toUserDTO(userEntity);
    }

    // Créer un utilisateur à partir d'un DTO
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        // Vérifier si le rôle existe en fonction du roleId
        Role role = roleRepository.findById(userDTO.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        System.out.println(role);
        // Convertir le UserDTO en UserEntity
        UserEntity userEntity = userMapper.fromUserDTO(userDTO);

        // Assigner le rôle à l'utilisateur
        userEntity.setRole(role);
        System.out.println(userEntity);
        return userMapper.toUserDTO(userRepository.save(userEntity));
    }

    // Mettre à jour un utilisateur avec les informations de son DTO
    @Transactional
    public UserDTO updateUser(long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userDTO.setId(id);
                    UserEntity updatedUser = userMapper.fromUserDTO(userDTO);
                    userRepository.save(updatedUser);
                    return userMapper.toUserDTO(updatedUser);
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault())));
    }

    // Supprimer un utilisateur par son ID
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);  // Supprimer l'utilisateur
    }

    // Suppression d'un utilisateur (en l'archivant)
    @Transactional
    public void archiveUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault())));

        // Marquer l'utilisateur comme archivé au lieu de le supprimer
        userEntity.setArchived(true);

        // Sauvegarder l'utilisateur avec le statut archivé
        userRepository.save(userEntity);
    }


    // Vérifier si un utilisateur existe par son ID
    @Transactional(readOnly = true)
    public boolean userExists(long id) {
        return userRepository.existsById(id);
    }

    // Vérifier si un utilisateur existe par son email
    @Transactional(readOnly = true)
    public boolean userExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
