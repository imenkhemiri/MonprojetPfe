package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Equipe;
import java.util.List;

public interface EquipeService {
    Equipe createEquipe(Equipe equipe);
    List<Equipe> getAllEquipes();
    Equipe getEquipeById(Long id);
    Equipe updateEquipe(Long id, Equipe equipe);
    void deleteEquipe(Long id);
    List<Equipe> getEquipesByClub(Long clubId);
    List<Equipe> getEquipesByGenre(String genre);
    List<Equipe> getEquipesByClubAndGenre(Long clubId, String genre);
}