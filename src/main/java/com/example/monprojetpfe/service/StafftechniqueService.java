package com.example.monprojetpfe.service;

import com.example.monprojetpfe.model.Stafftechnique;
import java.util.List;

public interface StafftechniqueService {
    Stafftechnique createStaff(Stafftechnique staff);
    List<Stafftechnique> getAllStaff();
    Stafftechnique getStaffById(Long id);
    Stafftechnique updateStaff(Long id, Stafftechnique staff);
    void deleteStaff(Long id);
    List<Stafftechnique> getStaffByClub(Long clubId);
    List<Stafftechnique> getStaffByType(String typeStaff);
}