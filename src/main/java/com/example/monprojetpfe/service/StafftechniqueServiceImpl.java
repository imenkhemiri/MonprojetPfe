package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Stafftechnique;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.StafftechniqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StafftechniqueServiceImpl implements StafftechniqueService {

    private final StafftechniqueRepository staffRepository;
    private final ClubRepository clubRepository;

    @Override
    public Stafftechnique createStaff(Stafftechnique staff) {
        if (staff.getClub() != null && staff.getClub().getId() != null) {
            Club club = clubRepository.findById(staff.getClub().getId())
                    .orElseThrow(() -> new RuntimeException("Club non trouvé"));
            staff.setClub(club);
        }
        return staffRepository.save(staff);
    }

    @Override
    public List<Stafftechnique> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Stafftechnique getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff non trouvé avec l'id: " + id));
    }

    @Override
    public Stafftechnique updateStaff(Long id, Stafftechnique staff) {
        Stafftechnique existing = getStaffById(id);
        if (staff.getNom() != null) existing.setNom(staff.getNom());
        if (staff.getPrenom() != null) existing.setPrenom(staff.getPrenom());
        if (staff.getTypeStaff() != null) existing.setTypeStaff(staff.getTypeStaff());
        if (staff.getQualification() != null) existing.setQualification(staff.getQualification());
        if (staff.getAnneeExperience() != null) existing.setAnneeExperience(staff.getAnneeExperience());
        if (staff.getStatut() != null) existing.setStatut(staff.getStatut());
        if (staff.getNationalite() != null) existing.setNationalite(staff.getNationalite());
        if (staff.getDateNaissance() != null) existing.setDateNaissance(staff.getDateNaissance());
        return staffRepository.save(existing);
    }

    @Override
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff non trouvé avec l'id: " + id);
        }
        staffRepository.deleteById(id);
    }

    @Override
    public List<Stafftechnique> getStaffByClub(Long clubId) {
        return staffRepository.findByClubId(clubId);
    }

    @Override
    public List<Stafftechnique> getStaffByType(String typeStaff) {
        return staffRepository.findByTypeStaff(typeStaff);
    }
}