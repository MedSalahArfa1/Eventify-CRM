package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.Role;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.services.servicesInter.RoleServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleServiceInter {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
