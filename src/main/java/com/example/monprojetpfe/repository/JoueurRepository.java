package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    List<Joueur> findByClubId(Long clubId);
    List<Joueur> findByStatut(String statut);
    List<Joueur> findByCategorie(String categorie);
}