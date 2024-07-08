package com.eventify.backend.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String ticketType;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;
}