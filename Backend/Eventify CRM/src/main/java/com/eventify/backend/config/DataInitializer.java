package com.eventify.backend.config;

import com.eventify.backend.entities.Role;
import com.eventify.backend.entities.Rolename;
import com.eventify.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role vendorRole = new Role();
            vendorRole.setRolename(Rolename.VENDOR);

            Role eventManagerRole = new Role();
            eventManagerRole.setRolename(Rolename.EVENTMANAGER);

            Role participantRole = new Role();
            participantRole.setRolename(Rolename.PARTICIPANT);

            Role eventStaffRole = new Role();
            eventStaffRole.setRolename(Rolename.EVENTSTAFF);

            roleRepository.save(vendorRole);
            roleRepository.save(eventManagerRole);
            roleRepository.save(participantRole);
            roleRepository.save(eventStaffRole);
        }
    }
}
