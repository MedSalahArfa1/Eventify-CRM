package com.eventify.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private List<TaskEntity> tasks;

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
