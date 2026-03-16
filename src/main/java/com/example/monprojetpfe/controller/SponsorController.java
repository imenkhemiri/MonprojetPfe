package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Sponsor;
import com.example.monprojetpfe.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @PostMapping
    public ResponseEntity<Sponsor> createSponsor(@RequestBody Sponsor sponsor) {
        return new ResponseEntity<>(sponsorService.createSponsor(sponsor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sponsor>> getAllSponsors() {
        return ResponseEntity.ok(sponsorService.getAllSponsors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable Long id) {
        return ResponseEntity.ok(sponsorService.getSponsorById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable Long id, @RequestBody Sponsor sponsor) {
        return ResponseEntity.ok(sponsorService.updateSponsor(id, sponsor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.ok("Sponsor supprimé avec succès.");
    }

    @GetMapping("/federation/{federationId}")
    public ResponseEntity<List<Sponsor>> getSponsorsByFederation(@PathVariable Long federationId) {
        return ResponseEntity.ok(sponsorService.getSponsorsByFederation(federationId));
    }

    @GetMapping("/secteur/{secteur}")
    public ResponseEntity<List<Sponsor>> getSponsorsBySecteur(@PathVariable String secteur) {
        return ResponseEntity.ok(sponsorService.getSponsorsBySecteur(secteur));
    }
}