package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByStatut(String statut);
    List<Match> findByClubDomicileId(Long clubId);
    List<Match> findByClubExterieurId(Long clubId);
}