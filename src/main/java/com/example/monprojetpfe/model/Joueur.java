package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "joueurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Joueur extends Membre{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categorie;
    private String genre;
    private String saison;
    private String statut;
    private String poste;
    private String age;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne
    @JoinColumn(name = "licence_id")
    private Licence licence;
}