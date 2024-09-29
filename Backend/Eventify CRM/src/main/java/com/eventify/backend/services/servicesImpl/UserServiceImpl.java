package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.dto.RegisterDT0;
import com.eventify.backend.dto.RoleDTO;
import com.eventify.backend.entities.AddressEntity;
import com.eventify.backend.entities.Role;
import com.eventify.backend.entities.UserEntity;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.repositories.UserRepository;
import com.eventify.backend.services.servicesInter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findById(role.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found")));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userDetails) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setBirthDate(userDetails.getBirthDate());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            Set<Role> roles = new HashSet<>();
            for (Role role : userDetails.getRoles()) {
                roles.add(roleRepository.findById(role.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found")));
            }
            user.setRoles(roles);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }



    @Override
    public ResponseEntity<UserEntity> registerUser(RegisterDT0 registerDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setBirthDate(registerDTO.getBirthDate());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());

        AddressEntity address = new AddressEntity();
        address.setStreet(registerDTO.getAddress().getStreet());
        address.setCity(registerDTO.getAddress().getCity());
        address.setZipCode(registerDTO.getAddress().getZipCode());
        address.setState(registerDTO.getAddress().getState());
        address.setCountry(registerDTO.getAddress().getCountry());
        user.setAddress(address);

        // Process roles
        Set<Role> roles = new HashSet<>();
        for (RoleDTO roleDTO : registerDTO.getRoles()) {
            Role role = roleRepository.findById(roleDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            roles.add(role);
        }
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
