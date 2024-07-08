package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.Role;
import com.eventify.backend.entities.UserEntity;
import com.eventify.backend.repositories.RoleRepository;
import com.eventify.backend.repositories.UserRepository;
import com.eventify.backend.services.servicesInter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    Set<Role> roles = new HashSet<>();

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
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findById(role.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found")));
        }
        user.setRoles(roles);
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encryptedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
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
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
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
    public ResponseEntity<UserEntity> login(String username, String password) {
        UserEntity user=userRepository.findByUsername(username).orElse(null);
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

        if(user!=null && passwordEncoder.matches(password,user.getPassword()))
        {
            return ResponseEntity.ok(user);

        }else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
