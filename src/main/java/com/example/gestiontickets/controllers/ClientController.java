package com.example.gestiontickets.controllers;

import com.example.gestiontickets.models.Ticket;
import com.example.gestiontickets.repository.TicketRepository;
import com.example.gestiontickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TicketRepository tr;

    // Lister les tickets une personne
    @PostMapping("/mytickets/{id}")
    public List<Ticket> mytickets(@PathVariable Long id)
    {
        List<Ticket> liste = tr.findAll();
        List<Ticket> mytickets = new ArrayList<>();
        List<Ticket> listetickets = new ArrayList<>();
        for(Ticket t : liste)
        {
            if(t.getUser().getId()==id)
            {
                mytickets.add(t);
            }
        }
        return mytickets;


    }

    //Ajouter une tickets
    @PostMapping("/add")
    public Ticket AddTicket(@RequestBody Ticket ticket){
        ticket.setStatut("Open");
        return tr.save(ticket);
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
