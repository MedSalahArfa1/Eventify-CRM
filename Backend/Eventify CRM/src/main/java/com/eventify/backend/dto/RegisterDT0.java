package com.eventify.backend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class RegisterDT0 {

    private String username;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String password;
    private String email;
    private String phone;

    private AddressDTO address;

    private Set<Long> roleIds;

}
