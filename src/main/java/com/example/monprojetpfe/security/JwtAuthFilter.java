package com.example.monprojetpfe.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // Récupérer le header Authorization
        String header = request.getHeader("Authorization");

        // Vérifier si header existe et commence par "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            // Extraire le token (enlever "Bearer ")
            String token = header.substring(7);

            // Valider le token
            if (jwtUtil.isValid(token)) {
                // Extraire email et role
                String email = jwtUtil.getSubject(token);
                String role = jwtUtil.getRole(token);

                // Créer une autorité Spring Security avec prefix "ROLE_"
                List<SimpleGrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                // Créer l'authentification
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Mettre l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Continuer la chaîne de filtres
        chain.doFilter(request, response);
    }
}