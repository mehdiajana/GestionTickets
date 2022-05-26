package com.example.gestiontickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    @Column(unique=true)
    private String username;
    private String prenom;
    @Column(unique=true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> affectedtickets;

    public User(Long id, String nom, String username, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
}
