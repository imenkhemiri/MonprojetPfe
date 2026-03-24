package com.example.monprojetpfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Registerresponse {
    private String message ;
    private String name ;
    private String email;
    private Long id;


}
