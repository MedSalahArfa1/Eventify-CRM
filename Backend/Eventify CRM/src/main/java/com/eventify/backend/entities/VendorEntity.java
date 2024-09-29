package com.eventify.backend.entities;

//import jakarta.persistence.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorEntity extends UserEntity{
    private String company_name;
    private String serviceCategory;
    private String serviceDescription;
}


