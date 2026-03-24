package com.example.monprojetpfe.dto;


import com.example.monprojetpfe.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String message;
    private String token;
    private String email;
    private Long user_id;
 private Role role ;

}
