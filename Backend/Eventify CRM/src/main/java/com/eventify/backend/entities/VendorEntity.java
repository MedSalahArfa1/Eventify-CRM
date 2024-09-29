package com.eventify.backend.entities;

//import jakarta.persistence.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;
    private String vendorName;
    private String companyName;
    private String email;
    private String phone;
    private String serviceCategory;
    private String serviceDescription;
}


