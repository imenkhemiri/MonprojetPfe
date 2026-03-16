package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Arbitre;
import java.util.List;

public interface ArbitreService {
    Arbitre createArbitre(Arbitre arbitre);
    List<Arbitre> getAllArbitres();
    Arbitre getArbitreById(Long id);
    Arbitre updateArbitre(Long id, Arbitre arbitre);
    void deleteArbitre(Long id);
    List<Arbitre> getArbitresByClub(Long clubId);
    List<Arbitre> getArbitresByDisponibilite(Boolean disponibilite);
}