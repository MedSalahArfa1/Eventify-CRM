package com.eventify.backend.dto;

import com.eventify.backend.entities.Role;
import com.eventify.backend.entities.UserEntity;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private UserEntity user;
    private Set<Long> roles;

    public AuthResponseDTO(String accessToken, UserEntity user, Set<Long> roles) {
        this.accessToken = accessToken;
        this.user = user;
        this.roles = roles;
    }
}
