package com.example.gestiontickets.controllers;

import com.example.gestiontickets.models.Ticket;
import com.example.gestiontickets.repository.TicketRepository;
import com.example.gestiontickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev")
public class DeveloppeurController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TicketRepository tr;

    //Afficher la liste des tickets affecter a un developpeur
    @GetMapping("/myaffected/{id}")
    public List<Ticket> myaffectedtickets(@PathVariable Long id)
    {
        List<Ticket> myaffected = ur.findById(id).get().getAffectedtickets();
        return myaffected;
    }

    //Changer le status d'une ticket de En cours a Resolu
    @GetMapping("/changeStatus/{id}")
    public Ticket resolu( @PathVariable Long id){

        Ticket d = tr.findById(id).get();
        d.setStatut("Resolu");
        tr.save(d);
        return d;
    }
    // Afficher le details d'une ticket
    @GetMapping("/ticket/{id}")
    public Ticket getTickets(@PathVariable Long id){
        return tr.findById(id).get();
    }



}
