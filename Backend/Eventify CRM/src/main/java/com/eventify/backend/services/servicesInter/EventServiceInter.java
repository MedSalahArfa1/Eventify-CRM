package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.EventEntity;
import java.util.List;

public interface EventServiceInter {

    public List<EventEntity> getAllEvents();
    public EventEntity getEventById(Long id);
    public EventEntity createEvent(EventEntity event);
    public EventEntity updateEvent(Long id, EventEntity eventDetails);
    public void deleteEvent(Long id);

}
