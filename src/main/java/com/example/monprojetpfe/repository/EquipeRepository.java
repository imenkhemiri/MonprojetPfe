package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    List<Equipe> findByClubId(Long clubId);
    List<Equipe> findByGenre(String genre);
    List<Equipe> findByStatut(String statut);
    List<Equipe> findByClubIdAndGenre(Long clubId, String genre);
}