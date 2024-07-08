package com.eventify.backend.controllers;

import com.eventify.backend.entities.EventEntity;
import com.eventify.backend.services.servicesInter.EventServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
        @Autowired
        private EventServiceInter eventService;

        @GetMapping
        public List<EventEntity> getAllEvents() {
            return eventService.getAllEvents();
        }

        @GetMapping("/{id}")
        public ResponseEntity<EventEntity> getEventById(@PathVariable Long id) {
            EventEntity event = eventService.getEventById(id);
            return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
        }

        @PostMapping
        public EventEntity createEvent(@RequestBody EventEntity event) {
            return eventService.createEvent(event);
        }

        @PutMapping("/{id}")
        public ResponseEntity<EventEntity> updateEvent(@PathVariable Long id, @RequestBody EventEntity eventDetails) {
            EventEntity updatedEvent = eventService.updateEvent(id, eventDetails);
            return updatedEvent != null ? ResponseEntity.ok(updatedEvent) : ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public void deleteEvent(@PathVariable Long id) {
            eventService.deleteEvent(id);
        }

}
