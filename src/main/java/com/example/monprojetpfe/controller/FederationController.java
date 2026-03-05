package com.example.monprojetpfe.controller;


import com.example.monprojetpfe.model.Federation;
import com.example.monprojetpfe.service.FederationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/federations")
@RequiredArgsConstructor

public class FederationController {
    private final FederationService federationService;


    @PreAuthorize("hasAnyRole('FEDERATION_ADMIN')")
    @PostMapping
    public ResponseEntity<Federation> createFederation(@RequestBody Federation federation) {
        return new ResponseEntity<>(federationService.createFederation(federation), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Federation>> getAllFederations() {
        return ResponseEntity.ok(federationService.getAllFederations());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Federation> getFederationById(@PathVariable Long id) {
        return ResponseEntity.ok(federationService.getFederationById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Federation> updateFederation(@PathVariable Long id,
                                                       @RequestBody Federation federation) {
        return ResponseEntity.ok(federationService.updateFederation(id, federation));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFederation(@PathVariable Long id) {
        federationService.deleteFederation(id);
        return ResponseEntity.ok("Fédération supprimée avec succès.");
    }
    @PatchMapping("/{id}/parametres")
    public ResponseEntity<Federation> patchParametres(@PathVariable Long id,
                                                      @RequestBody Federation federation) {
        return ResponseEntity.ok(federationService.patchFederation(id, federation));
    }


}

