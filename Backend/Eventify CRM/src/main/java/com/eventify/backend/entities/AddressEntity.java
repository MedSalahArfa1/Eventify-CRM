package com.eventify.backend.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UserEntity user;

    @OneToOne(mappedBy = "address")
    private EventEntity event;
}
