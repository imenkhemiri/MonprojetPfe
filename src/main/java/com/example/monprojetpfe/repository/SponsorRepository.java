package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    List<Sponsor> findByFederationId(Long federationId);
    List<Sponsor> findByClubId(Long clubId);
    List<Sponsor> findBySecteur(String secteur);
}