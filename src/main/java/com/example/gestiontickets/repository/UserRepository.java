package com.example.gestiontickets.repository;

import com.example.gestiontickets.models.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String Username);



}