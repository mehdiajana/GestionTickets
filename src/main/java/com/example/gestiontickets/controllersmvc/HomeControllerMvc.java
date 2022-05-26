package com.example.gestiontickets.controllersmvc;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.RoleRepository;
import com.example.gestiontickets.repository.UserRepository;
import com.example.gestiontickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeControllerMvc {
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rr;

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        if(u==null)
            return "connexion";
        else {
            return "connexion";
        }

    }
    @GetMapping("/profile")
    public String profile(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "profile";
    }
    @GetMapping("/inscription")
    public String inscription(Model m) {
        m.addAttribute("user", new User());
        return "inscription";
    }
    @GetMapping("/error")
    public String error(Model m) {
        m.addAttribute("user", new User());
        return "error";
    }
    @PostMapping("/inscription")
    public String inscription(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors())
            return "inscription";
        List<Role> roles = new ArrayList<>();

        roles.add(rr.findRoleByName("USER"));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:login";
    }

    @GetMapping
    public String home(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "header";
    }

}
