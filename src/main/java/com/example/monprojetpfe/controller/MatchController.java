package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Match;
import com.example.monprojetpfe.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matchs")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;
    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return new ResponseEntity<>(matchService.createMatch(match), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatchs() {
        return ResponseEntity.ok(matchService.getAllMatchs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        return ResponseEntity.ok(matchService.updateMatch(id, match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Match supprimé avec succès.");
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Match>> getMatchsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(matchService.getMatchsByStatut(statut));
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Match>> getMatchsByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(matchService.getMatchsByClub(clubId));
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<Match> updateStatut(@PathVariable Long id, @RequestParam String statut) {
        return ResponseEntity.ok(matchService.updateStatut(id, statut));
    }
}


