package com.eventify.backend.repositories;

import com.eventify.backend.entities.EventManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventManagerRepository extends JpaRepository<EventManagerEntity, Long> {
}
