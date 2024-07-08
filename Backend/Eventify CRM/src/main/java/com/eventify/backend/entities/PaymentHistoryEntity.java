package com.eventify.backend.entities;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "payment_history")
public class PaymentHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Double amount;
    private Date date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;
}
