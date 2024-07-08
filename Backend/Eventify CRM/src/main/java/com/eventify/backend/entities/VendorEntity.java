package com.eventify.backend.entities;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class VendorEntity extends UserEntity{
    private String company_name;
    private String serviceCategory;
    private String serviceDescription;
}
