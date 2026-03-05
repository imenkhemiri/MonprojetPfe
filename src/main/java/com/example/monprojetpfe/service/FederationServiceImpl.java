package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Federation;
import com.example.monprojetpfe.repository.FederationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FederationServiceImpl implements FederationService {

    private final FederationRepository federationRepository;

    @Override
    public Federation createFederation(Federation federation) {
        try {
            return federationRepository.save(federation);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la fédération: " + e.getMessage());
        }
    }

    @Override
    public List<Federation> getAllFederations() {
        try {
            return federationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des fédérations: " + e.getMessage());
        }
    }

    @Override
    public Federation getFederationById(Long id) {
        try {
            return federationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fédération non trouvée avec l'id: " + id));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la fédération avec l'id: " + id + " - " + e.getMessage());
        }
    }

    @Override
    public Federation updateFederation(Long id, Federation federation) {
        try {
            Federation existing = getFederationById(id);

            existing.setNom(federation.getNom());
            existing.setPays(federation.getPays());
            existing.setCode(federation.getCode());
            existing.setDevise(federation.getDevise());
            existing.setLangueOfficielle(federation.getLangueOfficielle());
            existing.setAnneeFondation(federation.getAnneeFondation());

            return federationRepository.save(existing);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la fédération avec l'id: " + id + " - " + e.getMessage());
        }
    }

    @Override
    public void deleteFederation(Long id) {
        try {
            if (!federationRepository.existsById(id)) {
                throw new RuntimeException("Fédération non trouvée avec l'id: " + id);
            }
            federationRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la fédération avec l'id: " + id + " - " + e.getMessage());
        }
    }
    @Override
    public Federation patchFederation(Long id, Federation federation) {
        try {
            Federation existing = getFederationById(id);

            if (federation.getDevise() != null) existing.setDevise(federation.getDevise());
            if (federation.getLangueOfficielle() != null) existing.setLangueOfficielle(federation.getLangueOfficielle());
            if (federation.getPays() != null) existing.setPays(federation.getPays());
            if (federation.getNom() != null) existing.setNom(federation.getNom());
            if (federation.getCode() != null) existing.setCode(federation.getCode());
            if (federation.getAnneeFondation() != null) existing.setAnneeFondation(federation.getAnneeFondation());

            return federationRepository.save(existing);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du patch de la fédération: " + e.getMessage());
        }
    }
}