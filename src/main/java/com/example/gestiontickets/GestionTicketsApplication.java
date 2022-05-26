package com.example.gestiontickets;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestionTicketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionTicketsApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
