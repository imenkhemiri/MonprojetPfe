package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ Utilisateur connecté peut voir son profil
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getProfile(email));
    }

    // ✅ Utilisateur connecté peut modifier son profil
    @PatchMapping("/profile")
    public ResponseEntity<User> updateProfile(Authentication authentication,
                                              @RequestBody User user) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.updateProfile(email, user));
    }

    // ✅ Super admin peut voir tous les utilisateurs
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}