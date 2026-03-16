package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff_technique")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stafftechnique extends Membre {
    private String typeStaff;
    private String qualification;
    private Integer anneeExperience;
    private String statut;
}