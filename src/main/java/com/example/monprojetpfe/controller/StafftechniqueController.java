package com.example.monprojetpfe.controller;

import com.example.monprojetpfe.model.Stafftechnique;
import com.example.monprojetpfe.service.StafftechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StafftechniqueController {

    private final StafftechniqueService staffService;

    @PostMapping
    public ResponseEntity<Stafftechnique> createStaff(@RequestBody Stafftechnique staff) {
        return new ResponseEntity<>(staffService.createStaff(staff), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Stafftechnique>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stafftechnique> getStaffById(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Stafftechnique> updateStaff(@PathVariable Long id, @RequestBody Stafftechnique staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff supprimé avec succès.");
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Stafftechnique>> getStaffByClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(staffService.getStaffByClub(clubId));
    }

    @GetMapping("/type/{typeStaff}")
    public ResponseEntity<List<Stafftechnique>> getStaffByType(@PathVariable String typeStaff) {
        return ResponseEntity.ok(staffService.getStaffByType(typeStaff));
    }
}