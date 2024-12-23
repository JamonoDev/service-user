package com.jamonodev.service_user.controller;

import com.jamonodev.service_user.dto.UserDTO;
import com.jamonodev.service_user.entities.UserEntity;
import com.jamonodev.service_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }
    // Récupérer tous les utilisateurs
    @GetMapping("/archives")
    public List<UserDTO> getArchivedUsers() {
        return userService.getArchivedUsers();
    }
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/id")
    public UserDTO getUser(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    // Récupérer un utilisateur par son email
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/role/{roleName}")
    public List<UserDTO> getAllUsersByRole(@PathVariable String roleName) {
        return userService.findByRoleName(roleName);
    }
    // Créer un utilisateur
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // Supprimer un utilisateur (mettre à jour la valeur `archived` à `true`)
    @PutMapping("/archive/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archiveUser(@PathVariable Long id) {
        userService.archiveUser(id);
    }

    // Supprimer un utilisateur (mettre à jour la valeur `archived` à `true`)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
