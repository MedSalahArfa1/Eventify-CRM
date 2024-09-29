package com.eventify.backend.services.servicesInter;

import com.eventify.backend.dto.LoginDTO;
import com.eventify.backend.dto.RegisterDT0;
import com.eventify.backend.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public interface AuthServiceInter {
    ResponseEntity<?> register(RegisterDT0 registerDTO);
    ResponseEntity<?> login(LoginDTO loginDto);
    ResponseEntity<?> logout();
    ResponseEntity<?> deleteUser(String username);
}