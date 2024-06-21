package com.eventify.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "sponsorships")
public class SponsorshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sponsorshipId;
    private String sponsorName;
    private Double amount;
    private Date date;
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @JsonIgnore
    @OneToOne
    private Image sponsorshipImage;
}
