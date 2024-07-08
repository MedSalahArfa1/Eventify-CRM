package com.eventify.backend.controllers;

import com.eventify.backend.dto.AddressDTO;
import com.eventify.backend.dto.AuthResponseDTO;
import com.eventify.backend.dto.LoginDTO;
import com.eventify.backend.dto.RegisterDT0;
import com.eventify.backend.entities.AddressEntity;
import com.eventify.backend.entities.Role;
import com.eventify.backend.entities.UserEntity;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.repositories.UserRepository;
import com.eventify.backend.security.JWTGenerator;
import com.eventify.backend.services.servicesInter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final RoleRepository roleRepository;

    @Autowired
    private UserServiceInter userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    /*
    @PostConstruct
    public void createDefaultAdminAccount() {
        if (!userRepository.existsByUsername("admin")) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@bridge.com");
            adminUser.setPassword(passwordEncoder.encode("admin")); // You can change the default password
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Bridge");
            adminUser.setCreationDate(new Date());
            UserRole adminRole = new UserRole();
            adminRole.setUserRoleName(UserRoleName.ADMIN);
            adminRole.setUserEntity(adminUser);

            //---Access
            adminRole.setEventManagement(true);
            adminRole.setEventManagement(true);
            //--------

            adminUser.setUserRole(adminRole);

            userRepository.save(adminUser);
        }
    }
    */


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtGenerator.generateToken(authentication);

            Optional<UserEntity> userOptional = userRepository.findByUsername(userDetails.getUsername());
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, user);
                return ResponseEntity.ok(authResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

        } catch (AuthenticationException e) {
            // Authentication failed, return an error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }




    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDT0 registerDTO) {
        // Map RegisterDTO to UserEntity
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setBirthDate(registerDTO.getBirthDate());
        user.setPassword(registerDTO.getPassword()); // make sure to encode this password
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());

        AddressDTO addressDTO = registerDTO.getAddress();
        if (addressDTO != null) {
            AddressEntity address = new AddressEntity();
            address.setStreet(addressDTO.getLocation());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setZipCode(addressDTO.getZipCode());
            address.setCountry(addressDTO.getCountry());
            user.setAddress(address);
        }

        Set<Role> roles = new HashSet<>();
        for (Long roleId : registerDTO.getRoleIds()) {
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        }
        user.setRoles(roles);

        userService.createUser(user);

        return ResponseEntity.ok("User registered successfully");
    }


    @DeleteMapping("delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        // Recherche de l'utilisateur dans la base de données
        Optional <UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            // L'utilisateur existe, le supprimer de la base de données
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok("User deleted successfully");
        } else {
            // L'utilisateur n'existe pas dans la base de données
            return ResponseEntity.notFound().build();
        }
    }


}
