package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.AccountStatus;
import com.example.monprojetpfe.model.Role;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService {

    @Autowired
    private UserRepository userRepository;

    //  Récupérer les fédérations en attente
    public List<User> getPendingFederations() {
        try {
            List<User> pending = userRepository.findByRoleAndStatus(Role.FEDERATION_ADMIN, AccountStatus.PENDING);
            if (pending.isEmpty()) {
                throw new RuntimeException("Aucune demande en attente");
            }
            return pending;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erreur lors de la récupération des demandes : " + e.getMessage());
        }
    }

    //  Approuver une fédération
    public void approveFederation(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + userId));

            if (user.getStatus() == AccountStatus.APPROVED) {
                throw new RuntimeException("Ce compte est déjà approuvé");
            }

            user.setStatus(AccountStatus.APPROVED);
            userRepository.save(user);

        } catch (RuntimeException e) {
            throw new RuntimeException("Erreur lors de l'approbation : " + e.getMessage());
        }
    }

    //  Rejeter une fédération
    public void rejectFederation(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + userId));

            if (user.getStatus() == AccountStatus.REJECTED) {
                throw new RuntimeException("Ce compte est déjà rejeté");
            }

            user.setStatus(AccountStatus.REJECTED);
            userRepository.save(user);

        } catch (RuntimeException e) {
            throw new RuntimeException("Erreur lors du rejet : " + e.getMessage());
        }
    }
}