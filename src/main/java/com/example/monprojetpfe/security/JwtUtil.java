package com.example.monprojetpfe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "votre_cle_secrete_tres_longue_au_moins_256_bits_pour_securite_maximale";
    private static final long EXPIRATION = 24 * 60 * 60 * 1000; // 24 heures

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraire email du token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Alias pour extractEmail (utilisé dans JwtAuthFilter)
    public String getSubject(String token) {
        return extractEmail(token);
    }

    // Extraire role du token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Alias pour extractRole (utilisé dans JwtAuthFilter)
    public String getRole(String token) {
        return extractRole(token);
    }

    // Valider token (utilisé dans JwtAuthFilter)
    public boolean isValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Valider token avec email spécifique
    public boolean validateToken(String token, String email) {
        try {
            return extractEmail(token).equals(email) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Extraire tous les claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Vérifier si token expiré
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    }
