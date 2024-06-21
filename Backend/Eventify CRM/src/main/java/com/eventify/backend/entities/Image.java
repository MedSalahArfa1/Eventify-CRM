package com.eventify.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Lob
    @Column(name = "picByte",columnDefinition = "LONGBLOB")
    byte [] picByte;

    @OneToOne
    @JoinTable(name="event_image")
    private EventEntity event;

    @OneToOne
    @JoinTable(name="sponsor_logo")
    private SponsorshipEntity sponsorship;
}