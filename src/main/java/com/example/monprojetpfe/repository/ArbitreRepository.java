package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Arbitre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArbitreRepository extends JpaRepository<Arbitre, Long> {
    List<Arbitre> findByClubId(Long clubId);
    List<Arbitre> findByniveau(String niveau);
    List<Arbitre> findByDisponibilite(Boolean disponibilite);
}
