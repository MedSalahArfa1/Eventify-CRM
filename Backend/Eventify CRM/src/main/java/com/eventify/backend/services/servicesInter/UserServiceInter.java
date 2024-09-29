package com.eventify.backend.services.servicesInter;

import com.eventify.backend.dto.RegisterDT0;
import com.eventify.backend.entities.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInter{
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(Long id, UserEntity userDetails);
    void deleteUser(Long id);
    //ResponseEntity<UserEntity> login(String username, String password);
    UserEntity getUserByUsername(String username);


    ResponseEntity<UserEntity> registerUser(RegisterDT0 registerDTO);
}
