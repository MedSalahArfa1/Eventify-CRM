package com.eventify.backend.repositories;

import com.eventify.backend.entities.EventStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStaffRepository extends JpaRepository<EventStaffEntity, Long> {
}
