package com.eventify.backend.dto;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDT0 {

    private String username;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String password;
    private String email;
    private String phone;

    private AddressDTO address = new AddressDTO();

    private Set<RoleDTO> roles;

}
