package com.eventify.backend.entities;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "notifications_history")
public class NotificationHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private String message;
    private Date dateTime;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}