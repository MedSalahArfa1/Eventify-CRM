package com.eventify.backend.controllers;

import com.eventify.backend.entities.Role;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.services.servicesInter.RoleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    @Autowired
    private RoleServiceInter roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}

