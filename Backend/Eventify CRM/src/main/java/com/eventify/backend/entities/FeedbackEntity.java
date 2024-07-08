package com.eventify.backend.entities;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "tasks")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private int rating;
    private String comments;
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "left_by", nullable = false)
    private UserEntity leftBy;

}