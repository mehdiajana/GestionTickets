package com.example.gestiontickets.controllersmvc;

import com.example.gestiontickets.models.Ticket;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.TicketRepository;
import com.example.gestiontickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/client/mvc")
public class ClientControllerMvc {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TicketRepository tr;

    // Lister les tickets une personne
    @GetMapping("/mytickets")
    public String mytickets(Model m)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        List<Ticket> liste = tr.findAll();
        List<Ticket> mytickets = new ArrayList<>();
        for(Ticket t : liste)
        {
            if(t.getUser().getId()==u.getId())
            {
                mytickets.add(t);
            }
        }
        m.addAttribute("tickets", mytickets);
        return "client/listetickets";


    }




    //Ajouter une tickets
    @GetMapping("/addticket")
    public String create(Model m) {
        m.addAttribute("ticket", new Ticket());
        return "client/createticket";
    }

    @PostMapping("/addticket")
    public String add(@ModelAttribute ("ticket") Ticket ticket) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        ticket.setUser(u);
        ticket.setAttr(false);
        ticket.setStatut("Open");
        tr.save(ticket);
        return "client/listetickets";
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
