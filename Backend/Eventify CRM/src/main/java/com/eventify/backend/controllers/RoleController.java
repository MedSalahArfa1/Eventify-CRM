package com.eventify.backend.controllers;

import com.eventify.backend.entities.Role;
import com.eventify.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add")
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

}

