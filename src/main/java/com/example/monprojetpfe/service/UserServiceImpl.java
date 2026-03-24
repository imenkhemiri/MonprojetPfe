package com.example.monprojetpfe.service;

import com.example.monprojetpfe.dto.RegisterClubAdmin;
import com.example.monprojetpfe.dto.RegisterFedrationAdmin;
import com.example.monprojetpfe.dto.Registerresponse;
import com.example.monprojetpfe.model.AccountStatus;
import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Role;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + email));
    }

    @Override
    public User updateProfile(String email, User user) {
        User existing = getProfile(email);
        if (user.getName() != null) existing.setName(user.getName());
        if (user.getPhone() != null) existing.setPhone(user.getPhone());
        return userRepository.save(existing);
    }
    @Override
    public Registerresponse registerClubAdmin(RegisterClubAdmin request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new Registerresponse("Email existe déjà", null, null, null);
        }

        User user =  User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .lastName(request.getLastName())
        .phone(request.getPhone())
        .role(Role.CLUB_ADMIN )
        .status(AccountStatus.PENDING)
        .build();



        User savedUser = userRepository.save(user);
        Club club = Club.builder()
                .ville(request.getVille())
                .anneeFondation(request.getAnneeFondation())
                .adminClub(savedUser)
                .build();
        clubRepository.save(club);
        return new Registerresponse(
                "Inscription réussie, en attente de validation",
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getId()
        );
    }
    @Override
    public Registerresponse registerFederationAdmin(RegisterFedrationAdmin request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new Registerresponse("Email existe déjà", null, null, null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRole(Role.FEDERATION_ADMIN);
        user.setStatus(AccountStatus.PENDING);

        User savedUser = userRepository.save(user);
        return new Registerresponse(
                "Inscription réussie, en attente de validation",
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getId()
        );
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}