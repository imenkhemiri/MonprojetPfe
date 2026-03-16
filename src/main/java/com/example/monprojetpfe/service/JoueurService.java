package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Joueur;
import java.util.List;

public interface JoueurService {
    Joueur createJoueur(Joueur joueur);
    List<Joueur> getAllJoueurs();
    Joueur getJoueurById(Long id);
    Joueur updateJoueur(Long id, Joueur joueur);
    void deleteJoueur(Long id);
    List<Joueur> getJoueursByClub(Long clubId);
    List<Joueur> getJoueursByStatut(String statut);
}