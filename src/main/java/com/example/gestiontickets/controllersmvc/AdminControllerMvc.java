package com.example.gestiontickets.controllersmvc;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.Ticket;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.RoleRepository;
import com.example.gestiontickets.repository.TicketRepository;
import com.example.gestiontickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/mvc")

public class AdminControllerMvc {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rr;

    @Autowired
    private TicketRepository ticketRepository;


    // liste de tout les utilisateur
    @GetMapping("/users")
    public String users(Model m) {
        m.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }


    //Find User By Id
    @GetMapping("/user/{id}")
    public String users(Model m,@PathVariable long id) {
        m.addAttribute("user", userRepository.findById(id).get());
        return "admin/user";
    }


    // liste de tout les bugs
    @GetMapping("/tickets")
    public String AllTickets(Model m) {
        m.addAttribute("tickets", ticketRepository.findAll());
        return "admin/tickets";
    }


    // Find Ticket by ID
    @GetMapping("/ticket/{id}")
    public String Ticket(Model m, @PathVariable long id) {
        m.addAttribute("ticket", ticketRepository.findById(id).get());
        return "admin/ticket";
    }


    // liste des bugs non attribués,
    @GetMapping("/tickets/attrFalse")
    public String AllTicketsno(Model m) {
        m.addAttribute("tickets", ticketRepository.getTicketsByAttrIsFalse());
        return "admin/tickets";
    }

    @GetMapping("tickets/attr/{idt}")
    public String attribuer(Model m, @PathVariable("idt") Long idt) {
        Role R = rr.findRoleByName("DEV");
        m.addAttribute("users", userRepository.findUserByRoles(R));
        m.addAttribute("idt",idt);
        return "admin/assignerticket";
    }

    // attribuer un ticket à un développeur
    @GetMapping("/affecter/{idu}/{idt}")
    public String attribuer(@PathVariable("idu") Long idu, @PathVariable("idt") Long idt){
        User u= userRepository.findById(idu).get();
        Ticket t = ticketRepository.findById(idt).get();
        u.getAffectedtickets().add(t);
        t.setAttr(true);
        t.setStatut("attribuer");
        userRepository.save(u) ;
        ticketRepository.save(t);
        return "redirect:/admin/mvc/tickets";

    }

    // supprimer Ticket
    @PostMapping("ticket/{id}")
    public String delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/admin/mvc/tickets";

    }




}
