package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Equipe;
import com.example.monprojetpfe.service.EquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    @PostMapping
    public ResponseEntity<Equipe> createEquipe(@RequestBody Equipe equipe) {
        return new ResponseEntity<>(equipeService.createEquipe(equipe), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        return ResponseEntity.ok(equipeService.getAllEquipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable Long id) {
        return ResponseEntity.ok(equipeService.getEquipeById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable Long id, @RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.updateEquipe(id, equipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipe(@PathVariable Long id) {
        equipeService.deleteEquipe(id);
        return ResponseEntity.ok("Equipe supprimée avec succès.");
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Equipe>> getEquipesByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(equipeService.getEquipesByClub(clubId));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Equipe>> getEquipesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(equipeService.getEquipesByGenre(genre));
    }

    @GetMapping("/club/{clubId}/genre/{genre}")
    public ResponseEntity<List<Equipe>> getEquipesByClubAndGenre(@PathVariable Long clubId, @PathVariable String genre) {
        return ResponseEntity.ok(equipeService.getEquipesByClubAndGenre(clubId, genre));
    }
}