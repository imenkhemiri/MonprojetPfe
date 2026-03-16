package com.example.monprojetpfe.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "arbitres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arbitre extends Membre {

    private String niveau;
    private Boolean disponibilite;
    private String statut;

    @Temporal(TemporalType.DATE)
    private Date certificationDate;
}