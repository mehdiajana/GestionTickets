package com.example.gestiontickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String urgence;
    private String envExe;
    private String logiciel;
    private String statut;
    private Boolean attr;

    @ManyToOne
    private User user;
}
