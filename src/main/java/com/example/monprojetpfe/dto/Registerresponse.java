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
    private String email ;
    private String name ;
    private String message;
    private Long id;


}
