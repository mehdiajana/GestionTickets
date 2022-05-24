package com.example.gestiontickets.controllers;

import com.example.gestiontickets.models.Ticket;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.TicketRepository;
import com.example.gestiontickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TicketRepository tr;
    //Afficher les tickets qui sont pas attribuer a aucun developpeur
    @GetMapping("/NonAttr")
    public List<Ticket> getTicketsNonAttr(){
        List<Ticket> liste = tr.findAll();
        List<Ticket> listetickets = new ArrayList<>();
        for(Ticket t : liste)
        {
            if(t.getAttr()==false)
            {
                listetickets.add(t);
            }
        }
        return listetickets;
    }

    //Attribuer une tickets a un developpeur et modifier l etat du ticket a affecte
    @GetMapping("/attribuer/{idT}/{idU}")
    public void SaveC(@PathVariable Long idT, @PathVariable Long idU) {
        User user=ur.findById(idU).get();
        Ticket t=tr.findById(idT).get();
        t.setStatut("En cours");
        t.setAttr(true);
        user.getAffectedtickets().add(t);
        tr.save(t);
        ur.save(user);
    }

    //Lister tous les utilisateurs
    @GetMapping("/users")
    public List<User> getUsers()
    {
        return ur.findAll();
    }

    // Afficher tous les tickets
    @GetMapping("/tickets")
    public List<Ticket> AllTickets(){
        return tr.findAll();
    }

    // Afficher le details d'une ticket
    @GetMapping("/ticket/{id}")
    public Ticket getTickets(@PathVariable Long id){
        return tr.findById(id).get();
    }

    //supprimer une tickets
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        tr.deleteById(id);
    }




}
