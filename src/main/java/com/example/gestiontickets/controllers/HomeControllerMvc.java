package com.example.gestiontickets.controllers;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.RoleRepository;
import com.example.gestiontickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/mvc")
public class HomeControllerMvc {
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository rr;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "connexion";
    }


    @GetMapping("/register")
    public String insc(Model m) {
        m.addAttribute("user",new User());
        return "inscription";
    }

    @PostMapping("register")
    public String insc(@ModelAttribute User user, BindingResult result) {
        List<Role> rolees  = new ArrayList<>();
        Role r= rr.findByName("USER");
        rolees.add(r);
        user.setRoles(rolees);
        if (result.hasErrors())
            return "inscription";

        userService.saveUser(user);
        return "redirect:login";
    }
}
