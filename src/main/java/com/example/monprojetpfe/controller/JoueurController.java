package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Joueur;
import com.example.monprojetpfe.service.JoueurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/joueurs")
@RequiredArgsConstructor
public class JoueurController {

    private final JoueurService joueurService;

    @PostMapping
    public ResponseEntity<Joueur> createJoueur(@RequestBody Joueur joueur) {
        return new ResponseEntity<>(joueurService.createJoueur(joueur), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Joueur>> getAllJoueurs() {
        return ResponseEntity.ok(joueurService.getAllJoueurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable Long id) {
        return ResponseEntity.ok(joueurService.getJoueurById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Joueur> updateJoueur(@PathVariable Long id, @RequestBody Joueur joueur) {
        return ResponseEntity.ok(joueurService.updateJoueur(id, joueur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJoueur(@PathVariable Long id) {
        joueurService.deleteJoueur(id);
        return ResponseEntity.ok("Joueur supprimé avec succès.");
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Joueur>> getJoueursByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(joueurService.getJoueursByClub(clubId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Joueur>> getJoueursByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(joueurService.getJoueursByStatut(statut));
    }
}