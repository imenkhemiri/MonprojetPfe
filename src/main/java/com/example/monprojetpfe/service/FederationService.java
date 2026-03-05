package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Federation;

import java.util.List;

public interface FederationService {
    Federation createFederation(Federation federation);
    List<Federation> getAllFederations();
    Federation getFederationById(Long id);
    Federation updateFederation(Long id, Federation federation);
    void deleteFederation(Long id);
    Federation patchFederation(Long id, Federation federation);
}
