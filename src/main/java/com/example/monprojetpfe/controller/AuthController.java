package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.dto.*;
import com.example.monprojetpfe.service.AuthService;
import com.example.monprojetpfe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register/club-admin")
    public ResponseEntity<Registerresponse> registerClubAdmin(@RequestBody RegisterClubAdmin request) {
        Registerresponse response = userService.registerClubAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/federation-admin")
    public ResponseEntity<Registerresponse> registerFederationAdmin(@RequestBody RegisterFedrationAdmin request) {
        Registerresponse response = userService.registerFederationAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return ResponseEntity.ok(response);
    }
}