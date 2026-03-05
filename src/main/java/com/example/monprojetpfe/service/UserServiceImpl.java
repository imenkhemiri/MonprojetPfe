package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}