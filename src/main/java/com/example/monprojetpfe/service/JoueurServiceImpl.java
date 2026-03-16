package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Joueur;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.JoueurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JoueurServiceImpl implements JoueurService {

    private final JoueurRepository joueurRepository;
    private final ClubRepository clubRepository;

    @Override
    public Joueur createJoueur(Joueur joueur) {
        if (joueur.getClub() != null && joueur.getClub().getId() != null) {
            Club club = clubRepository.findById(joueur.getClub().getId())
                    .orElseThrow(() -> new RuntimeException("Club non trouvé"));
            joueur.setClub(club);
        }
        return joueurRepository.save(joueur);
    }

    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    @Override
    public Joueur getJoueurById(Long id) {
        return joueurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Joueur non trouvé avec l'id: " + id));
    }

    @Override
    public Joueur updateJoueur(Long id, Joueur joueur) {
        Joueur existing = getJoueurById(id);
        if (joueur.getNom() != null) existing.setNom(joueur.getNom());
        if (joueur.getPrenom() != null) existing.setPrenom(joueur.getPrenom());
        if (joueur.getCategorie() != null) existing.setCategorie(joueur.getCategorie());
        if (joueur.getGenre() != null) existing.setGenre(joueur.getGenre());
        if (joueur.getAge() != null) existing.setAge(joueur.getAge());
        if (joueur.getSaison() != null) existing.setSaison(joueur.getSaison());
        if (joueur.getStatut() != null) existing.setStatut(joueur.getStatut());
        if (joueur.getNationalite() != null) existing.setNationalite(joueur.getNationalite());
        if (joueur.getPoste() != null) existing.setPoste(joueur.getPoste());
        if (joueur.getDateNaissance() != null) existing.setDateNaissance(joueur.getDateNaissance());
        return joueurRepository.save(existing);
    }

    @Override
    public void deleteJoueur(Long id) {
        if (!joueurRepository.existsById(id)) {
            throw new RuntimeException("Joueur non trouvé avec l'id: " + id);
        }
        joueurRepository.deleteById(id);
    }

    @Override
    public List<Joueur> getJoueursByClub(Long clubId) {
        return joueurRepository.findByClubId(clubId);
    }

    @Override
    public List<Joueur> getJoueursByStatut(String statut) {
        return joueurRepository.findByStatut(statut);
    }
}