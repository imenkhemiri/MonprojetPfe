package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Equipe;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;
    private final ClubRepository clubRepository;

    @Override
    public Equipe createEquipe(Equipe equipe) {
        if (equipe.getClub() != null && equipe.getClub().getId() != null) {
            Club club = clubRepository.findById(equipe.getClub().getId())
                    .orElseThrow(() -> new RuntimeException("Club non trouvé"));
            equipe.setClub(club);
        }
        if (equipe.getStatut() == null) {
            equipe.setStatut("ACTIVE");
        }
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipeById(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée avec l'id: " + id));
    }

    @Override
    public Equipe updateEquipe(Long id, Equipe equipe) {
        Equipe existing = getEquipeById(id);
        if (equipe.getNom() != null) existing.setNom(equipe.getNom());
        if (equipe.getGenre() != null) existing.setGenre(equipe.getGenre());
        if (equipe.getCategorie() != null) existing.setCategorie(equipe.getCategorie());
        if (equipe.getStatut() != null) existing.setStatut(equipe.getStatut());
        if (equipe.getSaison() != null) existing.setSaison(equipe.getSaison());
        return equipeRepository.save(existing);
    }

    @Override
    public void deleteEquipe(Long id) {
        if (!equipeRepository.existsById(id)) {
            throw new RuntimeException("Equipe non trouvée avec l'id: " + id);
        }
        equipeRepository.deleteById(id);
    }

    @Override
    public List<Equipe> getEquipesByClub(Long clubId) {
        return equipeRepository.findByClubId(clubId);
    }

    @Override
    public List<Equipe> getEquipesByGenre(String genre) {
        return equipeRepository.findByGenre(genre);
    }

    @Override
    public List<Equipe> getEquipesByClubAndGenre(Long clubId, String genre) {
        return equipeRepository.findByClubIdAndGenre(clubId, genre);
    }
}