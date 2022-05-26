package com.example.gestiontickets.repository;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String Username);
    List<User> findUserByRoles(Role r);



}