package com.eventify.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String posterUrl;
    private int totalTickets;
    private Date startDateTime;
    private Date endDateTime;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<FeedbackEntity> feedbacks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "event_manager",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<EventManagerEntity> eventManagers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "event_participant",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<ParticipantEntity> participants = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "event_staff",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<EventStaffEntity> eventStaffs = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private AddressEntity address;

    @JsonIgnore
    @OneToOne
    private Image eventImage;
}
