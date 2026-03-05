package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LicenceRepository extends JpaRepository<Licence, UUID> {
    List<Licence> findByClubId(Long clubId);
    List<Licence> findByUserId(Long userId);
    List<Licence> findByStatut(String statut);
    List<Licence> findByType(String type);
}