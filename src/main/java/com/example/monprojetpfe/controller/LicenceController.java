package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Licence;
import com.example.monprojetpfe.service.LicenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/licences")
@RequiredArgsConstructor
public class LicenceController {

    private final LicenceService licenceService;

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @PostMapping
    public ResponseEntity<Licence> createLicence(@RequestBody Licence licence) {
        return new ResponseEntity<>(licenceService.createLicence(licence), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Licence>> getAllLicences() {
        return ResponseEntity.ok(licenceService.getAllLicences());
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Licence> getLicenceById(@PathVariable UUID id) {
        return ResponseEntity.ok(licenceService.getLicenceById(id));
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Licence> updateLicence(@PathVariable UUID id,
                                                 @RequestBody Licence licence) {
        return ResponseEntity.ok(licenceService.updateLicence(id, licence));
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLicence(@PathVariable UUID id) {
        licenceService.deleteLicence(id);
        return ResponseEntity.ok("Licence supprimée avec succès.");
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Licence>> getLicencesByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(licenceService.getLicencesByClub(clubId));
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Licence>> getLicencesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(licenceService.getLicencesByUser(userId));
    }

    //@PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Licence>> getLicencesByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(licenceService.getLicencesByStatut(statut));
    }
}