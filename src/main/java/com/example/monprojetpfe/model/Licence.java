package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "licences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String numero;
    private String type;

    @Temporal(TemporalType.DATE)
    private Date dateEmission;

    @Temporal(TemporalType.DATE)
    private Date dateExpiration;

    private String statut;
    private boolean aptitudeMedicale;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}