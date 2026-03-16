package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Federation;
import com.example.monprojetpfe.model.Sponsor;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.FederationRepository;
import com.example.monprojetpfe.repository.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;
    private final FederationRepository federationRepository;
    private final ClubRepository clubRepository;

    @Override
    public Sponsor createSponsor(Sponsor sponsor) {
        if (sponsor.getFederation() != null && sponsor.getFederation().getId() != null) {
            Federation federation = federationRepository.findById(sponsor.getFederation().getId())
                    .orElseThrow(() -> new RuntimeException("Fédération non trouvée"));
            sponsor.setFederation(federation);
        }
        if (sponsor.getClub() != null && sponsor.getClub().getId() != null) {
            Club club = clubRepository.findById(sponsor.getClub().getId())
                    .orElseThrow(() -> new RuntimeException("Club non trouvé"));
            sponsor.setClub(club);
        }
        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sponsor non trouvé avec l'id: " + id));
    }

    @Override
    public Sponsor updateSponsor(Long id, Sponsor sponsor) {
        Sponsor existing = getSponsorById(id);
        if (sponsor.getNom() != null) existing.setNom(sponsor.getNom());
        if (sponsor.getSecteur() != null) existing.setSecteur(sponsor.getSecteur());
        if (sponsor.getEmailContact() != null) existing.setEmailContact(sponsor.getEmailContact());
        if (sponsor.getUrlLogo() != null) existing.setUrlLogo(sponsor.getUrlLogo());
        return sponsorRepository.save(existing);
    }

    @Override
    public void deleteSponsor(Long id) {
        if (!sponsorRepository.existsById(id)) {
            throw new RuntimeException("Sponsor non trouvé avec l'id: " + id);
        }
        sponsorRepository.deleteById(id);
    }

    @Override
    public List<Sponsor> getSponsorsByFederation(Long federationId) {
        return sponsorRepository.findByFederationId(federationId);
    }

    @Override
    public List<Sponsor> getSponsorsBySecteur(String secteur) {
        return sponsorRepository.findBySecteur(secteur);
    }

    @Override
    public List<Sponsor> getSponsorsByClub(Long clubId) {
        return sponsorRepository.findByClubId(clubId);
    }
}