package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByFederationId(Long federationId);
}