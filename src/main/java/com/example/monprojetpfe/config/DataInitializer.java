package com.example.monprojetpfe.config;


import com.example.monprojetpfe.model.AccountStatus;
import com.example.monprojetpfe.model.Role;
import com.example.monprojetpfe.model.User;
import com.example.monprojetpfe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${superAdmin.name}")
    private String name;
    @Value("${superAdmin.email}")
    private String email;
    @Value("${superAdmin.password}")
    private String password;
    @Value("${superAdmin.lastName}")
    private String lastName;



    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            var user = User.builder()
                    .name(name)
                    .lastName(lastName)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(Role.SUPER_ADMIN)
                    .status(AccountStatus.APPROVED)
                    .build();
            userRepository.save(user);


        }
    }
}