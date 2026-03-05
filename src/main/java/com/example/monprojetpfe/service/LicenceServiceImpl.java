package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Licence;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.LicenceRepository;
import com.example.monprojetpfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenceServiceImpl implements LicenceService {

    private final LicenceRepository licenceRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Override
    public Licence createLicence(Licence licence) {
        try {
            if (licence.getClub() != null && licence.getClub().getId() != null) {
                Club club = clubRepository.findById(licence.getClub().getId())
                        .orElseThrow(() -> new RuntimeException("Club non trouvé"));
                licence.setClub(club);
            }
            if (licence.getUser() != null && licence.getUser().getId() != null) {
                User user = userRepository.findById(licence.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                licence.setUser(user);
            }
              Licence licenceSaved= licenceRepository. save(licence);
            return licenceSaved ;

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la licence: " + e.getMessage());
        }
    }

    @Override
    public List<Licence> getAllLicences() {
        return licenceRepository.findAll();
    }

    @Override
    public Licence getLicenceById(UUID id) {
        return licenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licence non trouvée avec l'id: " + id));
    }

    @Override
    public Licence updateLicence(UUID id, Licence licence) {
        Licence existing = getLicenceById(id);
        if (licence.getNumero() != null) existing.setNumero(licence.getNumero());
        if (licence.getType() != null) existing.setType(licence.getType());
        if (licence.getDateEmission() != null) existing.setDateEmission(licence.getDateEmission());
        if (licence.getDateExpiration() != null) existing.setDateExpiration(licence.getDateExpiration());
        if (licence.getStatut() != null) existing.setStatut(licence.getStatut());
        existing.setAptitudeMedicale(licence.isAptitudeMedicale());
        return licenceRepository.save(existing);
    }

    @Override
    public void deleteLicence(UUID id) {
        if (!licenceRepository.existsById(id)) {
            throw new RuntimeException("Licence non trouvée avec l'id: " + id);
        }
        licenceRepository.deleteById(id);
    }

    @Override
    public List<Licence> getLicencesByClub(Long clubId) {
        return licenceRepository.findByClubId(clubId);
    }

    @Override
    public List<Licence> getLicencesByUser(Long userId) {
        return licenceRepository.findByUserId(userId);
    }

    @Override
    public List<Licence> getLicencesByStatut(String statut) {
        return licenceRepository.findByStatut(statut);
    }
}
