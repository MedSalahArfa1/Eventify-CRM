package com.eventify.backend.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String location;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;
}
