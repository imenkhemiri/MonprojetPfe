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
            return ResponseEntity.ok("Fédération approuvée ✅");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/reject/{userId}")
    public ResponseEntity<String> reject(@PathVariable Long userId) {
        try {
            superAdminService.rejectFederation(userId);
            return ResponseEntity.ok("Fédération rejetée ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}