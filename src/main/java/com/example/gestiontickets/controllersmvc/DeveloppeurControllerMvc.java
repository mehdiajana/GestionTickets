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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dev/mvc")
public class DeveloppeurControllerMvc {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TicketRepository tr;

    //Afficher la liste des tickets affecter a un developpeur

    @GetMapping("/myaffected")
    public String mytickets(Model m)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = ur.findUserByUsername(auth.getName());
        List<Ticket> myaffected = u.getAffectedtickets();
        m.addAttribute("tickets", myaffected);
        return "developpeur/tickets";


    }

    //Changer le status d'une ticket de En cours a Resolu
    @GetMapping("/changeStatus/{id}")
    public String resolu( Model m, @PathVariable Long id){

        Ticket d = tr.findById(id).get();
        d.setStatut("Resolu");
        tr.save(d);
        return "redirect:/dev/mvc/myaffected";
    }




}
