package com.example.monprojetpfe.repository;

import com.example.monprojetpfe.model.AccountStatus;
import com.example.monprojetpfe.model.Role;
import com.example.monprojetpfe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByName(String name);
    List<User> findByLastName(String lastName);
    Optional<User> findByphone(String phone);
    List<User> findByRole(Role role);
    List<User> findByRoleAndStatus(Role role, AccountStatus status);
    List<User>findByStatus(AccountStatus status);

}
