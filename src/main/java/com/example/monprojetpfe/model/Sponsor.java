package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sponsors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String secteur;
    private String emailContact;
    private String urlLogo;

    @ManyToOne
    @JoinColumn(name = "federation_id")
    private Federation federation;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}