package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.dto.*;
import com.eventify.backend.entities.*;
import com.eventify.backend.repositories.*;
import com.eventify.backend.security.JWTGenerator;
import com.eventify.backend.services.servicesInter.AuthServiceInter;
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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthServiceInter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDto) {
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
                Set<Long> roles = user.getRoles().stream()
                        .map(Role::getRoleId)
                        .collect(Collectors.toSet());
                AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, user, roles);
                return ResponseEntity.ok(authResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @Override
    public ResponseEntity<?> logout() {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterDT0 registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setBirthDate(registerDTO.getBirthDate());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());

        AddressDTO addressDTO = registerDTO.getAddress();
        if (addressDTO != null) {
            AddressEntity address = new AddressEntity();
            address.setName(addressDTO.getName());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setZipCode(addressDTO.getZipCode());
            address.setCountry(addressDTO.getCountry());
            user.setAddress(address);
        }

        // Process roles
        Set<Role> roles = new HashSet<>();
        for (RoleDTO roleDTO : registerDTO.getRoles()) {
            Role role = roleRepository.findById(roleDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            roles.add(role);
        }

        try {
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
