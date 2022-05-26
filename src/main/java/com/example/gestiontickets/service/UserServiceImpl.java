package com.example.gestiontickets.service;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;
import com.example.gestiontickets.repository.RoleRepository;
import com.example.gestiontickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@XSlf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    private UserRepository ur ;

    @Autowired
    private RoleRepository rr ;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ur.findUserByUsername(username);
        if(user ==null)
        {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.error("User found in the database {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       // Role r= rr.findByName("User");
        //user.setRoles((List<Role>) r);
        return ur.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        return rr.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = ur.findUserByUsername(username);
        Role role = rr.findRoleByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return ur.findUserByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return ur.findAll();
    }


}