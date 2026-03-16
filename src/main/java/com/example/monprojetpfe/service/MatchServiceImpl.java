package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Club;
import com.example.monprojetpfe.model.Match;
import com.example.monprojetpfe.repository.ClubRepository;
import com.example.monprojetpfe.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;

    @Override
    public Match createMatch(Match match) {
        if (match.getClubDomicile() != null && match.getClubDomicile().getId() != null) {
            Club club = clubRepository.findById(match.getClubDomicile().getId())
                    .orElseThrow(() -> new RuntimeException("Club domicile non trouvé"));
            match.setClubDomicile(club);
        }
        if (match.getClubExterieur() != null && match.getClubExterieur().getId() != null) {
            Club club = clubRepository.findById(match.getClubExterieur().getId())
                    .orElseThrow(() -> new RuntimeException("Club extérieur non trouvé"));
            match.setClubExterieur(club);
        }
        if (match.getStatut() == null) {
            match.setStatut("PLANIFIE");
        }
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatchs() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match non trouvé avec l'id: " + id));
    }

    @Override
    public Match updateMatch(Long id, Match match) {
        Match existing = getMatchById(id);
        if (match.getDateMatch() != null) existing.setDateMatch(match.getDateMatch());
        if (match.getLieu() != null) existing.setLieu(match.getLieu());
        if (match.getScoreDomicile() != null) existing.setScoreDomicile(match.getScoreDomicile());
        if (match.getScoreExterieur() != null) existing.setScoreExterieur(match.getScoreExterieur());
        if (match.getStatut() != null) existing.setStatut(match.getStatut());
        return matchRepository.save(existing);
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match non trouvé avec l'id: " + id);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public List<Match> getMatchsByStatut(String statut) {
        return matchRepository.findByStatut(statut);
    }

    @Override
    public List<Match> getMatchsByClub(Long clubId) {
        List<Match> domicile = matchRepository.findByClubDomicileId(clubId);
        List<Match> exterieur = matchRepository.findByClubExterieurId(clubId);
        List<Match> tous = new ArrayList<>(domicile);
        tous.addAll(exterieur);
        return tous;
    }

    @Override
    public Match updateStatut(Long id, String statut) {
        Match existing = getMatchById(id);
        existing.setStatut(statut);
        return matchRepository.save(existing);
    }
}