package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Arbitre;
import com.example.monprojetpfe.service.ArbitreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/arbitres")
@RequiredArgsConstructor
public class ArbitreController {

    private final ArbitreService arbitreService;

    @PostMapping
    public ResponseEntity<Arbitre> createArbitre(@RequestBody Arbitre arbitre) {
        return new ResponseEntity<>(arbitreService.createArbitre(arbitre), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Arbitre>> getAllArbitres() {
        return ResponseEntity.ok(arbitreService.getAllArbitres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arbitre> getArbitreById(@PathVariable Long id) {
        return ResponseEntity.ok(arbitreService.getArbitreById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Arbitre> updateArbitre(@PathVariable Long id, @RequestBody Arbitre arbitre) {
        return ResponseEntity.ok(arbitreService.updateArbitre(id, arbitre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArbitre(@PathVariable Long id) {
        arbitreService.deleteArbitre(id);
        return ResponseEntity.ok("Arbitre supprimé avec succès.");
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Arbitre>> getArbitresByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(arbitreService.getArbitresByClub(clubId));
    }

    @GetMapping("/disponibilite/{disponibilite}")
    public ResponseEntity<List<Arbitre>> getArbitresByDisponibilite(@PathVariable Boolean disponibilite) {
        return ResponseEntity.ok(arbitreService.getArbitresByDisponibilite(disponibilite));
    }
}