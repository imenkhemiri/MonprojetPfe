package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.Stafftechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StafftechniqueRepository extends JpaRepository<Stafftechnique, Long> {
    List<Stafftechnique> findByClubId(Long clubId);
    List<Stafftechnique> findByTypeStaff(String typeStaff);
    List<Stafftechnique> findByStatut(String statut);
}