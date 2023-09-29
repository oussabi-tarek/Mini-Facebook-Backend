package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import com.minifacebookbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ResourcePath.USER)
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserRepresentation>> getAllUsers() {
        log.info("Recuperation de tous les utilisateurs");
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRepresentation> getById(@PathVariable String userId){
        log.info("Recuperation d'un utilisateur par id {}",userId);
        return ResponseEntity.ok(userService.getById(userId));
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserCommand userCommand) {
        log.info("Enregistrement d'un utilisateur {}",userCommand);
        return ResponseEntity.ok(userService.saveUser(userCommand));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserCommand userCommand, @PathVariable String userId) {
        log.info("Mise à jour d'un utilisateur {}",userCommand);
        return ResponseEntity.ok(userService.updateUser(userCommand, userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        log.info("Suppression d'un utilisateur {}",userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok("utilisateur supprimé avec succès");
    }

}
