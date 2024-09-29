package com.eventify.backend.entities;

//import jakarta.persistence.*;
import javax.persistence.*;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private Rolename rolename;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<UserEntity> users =new HashSet<>();

}
