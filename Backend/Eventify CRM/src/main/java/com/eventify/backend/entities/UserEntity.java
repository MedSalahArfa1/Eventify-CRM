package com.eventify.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String password;
    private String email;
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    @JsonIgnore
    private AddressEntity address;

    @JsonIgnore
    @OneToOne
    private Image userImage;

}
