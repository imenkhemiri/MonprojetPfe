package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.service.ClubInterfaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubInterfaceService clubService;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club clubSaved = clubService.createClub(club);
        if (clubSaved == null)return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(clubSaved, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/clubs/federation/{federationId}")
    public ResponseEntity<List<Club>> getClubsByFederation(@PathVariable Long federationId) {
        return ResponseEntity.ok(clubService.getClubsByFederation(federationId));
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/clubs/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

     //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @PutMapping("/clubs/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        return ResponseEntity.ok(clubService.updateClub(id, club));
    }

   // @PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @DeleteMapping("/clubs/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok("Club supprimé avec succès.");
    }
}