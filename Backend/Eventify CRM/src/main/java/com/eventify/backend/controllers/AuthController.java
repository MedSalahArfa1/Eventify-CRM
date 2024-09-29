package com.eventify.backend.controllers;

import com.eventify.backend.dto.LoginDTO;
import com.eventify.backend.dto.RegisterDT0;
import com.eventify.backend.entities.UserEntity;
import com.eventify.backend.services.servicesInter.AuthServiceInter;
import com.eventify.backend.services.servicesInter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthServiceInter authService;
    @Autowired
    private UserServiceInter userService;

    @Autowired
    public AuthController(AuthServiceInter authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout() {
        return authService.logout();
    }

    @PostMapping("register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterDT0 registerDTO) {
        return userService.registerUser(registerDTO);
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return authService.deleteUser(username);
    }
}
