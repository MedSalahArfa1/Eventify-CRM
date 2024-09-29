package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.Role;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.services.servicesInter.RoleServiceInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleServiceInter {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        logger.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            logger.info("No roles found");
        } else {
            logger.info("Roles found: {}", roles.size());
        }
        return roles;
    }
}
