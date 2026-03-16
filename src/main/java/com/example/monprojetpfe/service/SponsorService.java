package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Sponsor;
import java.util.List;

public interface SponsorService {
    Sponsor createSponsor(Sponsor sponsor);
    List<Sponsor> getAllSponsors();
    Sponsor getSponsorById(Long id);
    List<Sponsor> getSponsorsByClub(Long clubId);
    Sponsor updateSponsor(Long id, Sponsor sponsor);
    void deleteSponsor(Long id);
    List<Sponsor> getSponsorsByFederation(Long federationId);
    List<Sponsor> getSponsorsBySecteur(String secteur);
}