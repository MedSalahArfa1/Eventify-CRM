package com.eventify.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String location;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;

    @OneToOne(mappedBy = "address")
    private UserEntity user;

    @OneToOne(mappedBy = "address")
    private EventEntity event;
}
