package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "matchs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMatch;

    private String lieu;
    private Integer scoreDomicile;
    private Integer scoreExterieur;
    private String statut; // PLANIFIE, EN_COURS, TERMINE, ANNULE

    @ManyToOne
    @JoinColumn(name = "club_domicile_id")
    private Club clubDomicile;

    @ManyToOne
    @JoinColumn(name = "club_exterieur_id")
    private Club clubExterieur;
}