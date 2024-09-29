package com.eventify.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;
}
