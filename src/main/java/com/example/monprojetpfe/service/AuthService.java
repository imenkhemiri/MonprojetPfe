package com.example.monprojetpfe.service;

import com.example.monprojetpfe.dto.AuthResponse;
import com.example.monprojetpfe.dto.LoginRequest;
import com.example.monprojetpfe.dto.RegisterRequest;
import com.example.monprojetpfe.dto.Registerresponse;
import com.example.monprojetpfe.model.AccountStatus;
import com.example.monprojetpfe.model.Role;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.UserRepository;
import com.example.monprojetpfe.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ─── REGISTER ─────────────────────────────────────────────────
    public Registerresponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new Registerresponse("Email existe déjà", null, null, null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setRole(Role.FEDERATION_ADMIN);
        user.setStatus(AccountStatus.PENDING);

        userRepository.save(user);


        return new Registerresponse("Inscription réussie, en attente de validation", null, user.getEmail(), user.getId());
    }

    // ─── LOGIN ────────────────────────────────────────────────────
    public AuthResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return new AuthResponse("Email incorrect", null, null, null);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Mot de passe incorrect", null, null, null);
        }
        if (user.getStatus() == AccountStatus.PENDING) {
            return new AuthResponse("Votre compte est en attente de validation", null, null, null);
        }

        if (user.getStatus() == AccountStatus.REJECTED) {
            return new AuthResponse("Votre compte a été rejeté", null, null, null);
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse("Connexion réussie", token, user.getEmail(), user.getId());
    }
}