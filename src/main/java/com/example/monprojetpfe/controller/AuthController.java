package com.example.monprojetpfe.controller;


import com.example.monprojetpfe.dto.AuthResponse;
import com.example.monprojetpfe.dto.LoginRequest;
import com.example.monprojetpfe.dto.RegisterRequest;
import com.example.monprojetpfe.dto.Registerresponse;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.service.AuthService;
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


    @PostMapping("/register")
    public Registerresponse registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/register/FederationAdmin")
    public ResponseEntity<Registerresponse> registerFederationAdmin(@RequestBody RegisterRequest request) {
        Registerresponse response = authService.register(request);
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

