package com.example.gestiontickets.service;

import com.example.gestiontickets.models.Role;
import com.example.gestiontickets.models.User;

import java.util.List;


public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}