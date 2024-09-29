package com.eventify.backend.controllers;

import com.eventify.backend.entities.Role;
import com.eventify.backend.services.servicesInter.RoleServiceInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleServiceInter roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        logger.info("Received request to fetch all roles");
        List<Role> roles = roleService.getAllRoles();
        if (roles.isEmpty()) {
            logger.info("No roles returned from service");
        } else {
            logger.info("Returning roles: {}", roles.size());
        }
        return roles;
    }
}