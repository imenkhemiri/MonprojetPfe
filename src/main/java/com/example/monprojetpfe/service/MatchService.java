package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Match;
import java.util.List;

public interface MatchService {
    Match createMatch(Match match);
    List<Match> getAllMatchs();
    Match getMatchById(Long id);
    Match updateMatch(Long id, Match match);
    void deleteMatch(Long id);
    List<Match> getMatchsByStatut(String statut);
    List<Match> getMatchsByClub(Long clubId);
    Match updateStatut(Long id, String statut);
}