package com.eventify.backend.entities;

//import jakarta.persistence.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String name;
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
