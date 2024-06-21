package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.UserEntity;
import java.util.List;

public interface UserServiceInter{
    public List<UserEntity> getAllUsers();
    public UserEntity getUserById(Long id);
    public UserEntity createUser(UserEntity user);
    public UserEntity updateUser(Long id, UserEntity userDetails);
    public void deleteUser(Long id);
}
