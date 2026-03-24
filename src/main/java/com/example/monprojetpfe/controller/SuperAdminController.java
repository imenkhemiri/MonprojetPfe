package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingFederations() {
        try {
            List<User> pending = superAdminService.getPendingFederations();
            return ResponseEntity.ok(pending);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/approve/{userId}")
    public ResponseEntity<String> approve(@PathVariable Long userId) {
        try {
            superAdminService.approveFederation(userId);
            return ResponseEntity.ok("Fédération approuvée");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/reject/{userId}")
    public ResponseEntity<String> reject(@PathVariable Long userId) {
        try {
            superAdminService.rejectFederation(userId);
            return ResponseEntity.ok("Fédération rejetée");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        try {
            superAdminService.blockUser(userId);
            return ResponseEntity.ok("Compte bloqué avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/unblock/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable Long userId) {
        try {
            superAdminService.unblockUser(userId);
            return ResponseEntity.ok("Compte débloqué avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ← retourne tous les statuts
    @GetMapping("/approved-users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(superAdminService.getAllUsers());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}