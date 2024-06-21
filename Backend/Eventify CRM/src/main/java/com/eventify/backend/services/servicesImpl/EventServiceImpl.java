package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.EventEntity;
import com.eventify.backend.repositories.EventRepository;
import com.eventify.backend.services.servicesInter.EventServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventServiceInter {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public EventEntity createEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity updateEvent(Long id, EventEntity eventDetails) {
        EventEntity event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEventName(eventDetails.getEventName());
            event.setEventDescription(eventDetails.getEventDescription());
            event.setPosterUrl(eventDetails.getPosterUrl());
            event.setTotalTickets(eventDetails.getTotalTickets());
            event.setStartDateTime(eventDetails.getStartDateTime());
            event.setEndDateTime(eventDetails.getEndDateTime());
            event.setStatus(eventDetails.getStatus());
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}

