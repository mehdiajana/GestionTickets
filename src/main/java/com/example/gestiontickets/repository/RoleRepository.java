package com.example.gestiontickets.repository;

import com.example.gestiontickets.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String Name);
}
