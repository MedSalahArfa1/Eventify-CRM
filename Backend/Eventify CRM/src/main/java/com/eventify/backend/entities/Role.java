package com.eventify.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private Rolename rolename;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users =new HashSet<>();

}
