// RegsiterClubAdmin.java
package com.example.monprojetpfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterClubAdmin {


    private String nom;
    private String ville;
    private Integer anneeFondation;


    private String email;
    private String password;
    private String name;
    private String lastName;
    private String phone;
    private String pays;
    private String genre;
    private String nationalite;
    private String adresse;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
}