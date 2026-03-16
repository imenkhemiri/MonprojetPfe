package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "membres")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;

    @Column(unique = true)
    private String email;

    private String telephone;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String nationalite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    private String urlPhoto;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}