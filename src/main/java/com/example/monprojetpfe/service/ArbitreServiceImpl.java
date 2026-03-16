package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Arbitre;
import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.repository.ArbitreRepository;
import com.example.monprojetpfe.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArbitreServiceImpl implements ArbitreService {

    private final ArbitreRepository arbitreRepository;
    private final ClubRepository clubRepository;

    @Override
    public Arbitre createArbitre(Arbitre arbitre) {
        if (arbitre.getClub() != null && arbitre.getClub().getId() != null) {
            Club club = clubRepository.findById(arbitre.getClub().getId())
                    .orElseThrow(() -> new RuntimeException("Club non trouvé"));
            arbitre.setClub(club);
        }
        return arbitreRepository.save(arbitre);
    }

    @Override
    public List<Arbitre> getAllArbitres() {
        return arbitreRepository.findAll();
    }

    @Override
    public Arbitre getArbitreById(Long id) {
        return arbitreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arbitre non trouvé avec l'id: " + id));
    }

    @Override
    public Arbitre updateArbitre(Long id, Arbitre arbitre) {
        Arbitre existing = getArbitreById(id);
        if (arbitre.getNom() != null) existing.setNom(arbitre.getNom());
        if (arbitre.getPrenom() != null) existing.setPrenom(arbitre.getPrenom());
        if (arbitre.getNiveau() != null) existing.setNiveau(arbitre.getNiveau());
        if (arbitre.getDisponibilite() != null) existing.setDisponibilite(arbitre.getDisponibilite());
        if (arbitre.getStatut() != null) existing.setStatut(arbitre.getStatut());
        if (arbitre.getNationalite() != null) existing.setNationalite(arbitre.getNationalite());
        if (arbitre.getCertificationDate() != null) existing.setCertificationDate(arbitre.getCertificationDate());
        if (arbitre.getDateNaissance() != null) existing.setDateNaissance(arbitre.getDateNaissance());
        return arbitreRepository.save(existing);
    }

    @Override
    public void deleteArbitre(Long id) {
        if (!arbitreRepository.existsById(id)) {
            throw new RuntimeException("Arbitre non trouvé avec l'id: " + id);
        }
        arbitreRepository.deleteById(id);
    }

    @Override
    public List<Arbitre> getArbitresByClub(Long clubId) {
        return arbitreRepository.findByClubId(clubId);
    }

    @Override
    public List<Arbitre> getArbitresByDisponibilite(Boolean disponibilite) {
        return arbitreRepository.findByDisponibilite(disponibilite);
    }
}