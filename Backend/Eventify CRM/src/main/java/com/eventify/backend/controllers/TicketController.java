package com.eventify.backend.controllers;

import com.eventify.backend.entities.TicketEntity;
import com.eventify.backend.services.servicesInter.TicketServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {
    @Autowired
    private TicketServiceInter ticketService;

    @GetMapping
    public List<TicketEntity> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketEntity> getTicketById(@PathVariable Long id) {
        TicketEntity ticket = ticketService.getTicketById(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public TicketEntity createTicket(@RequestBody TicketEntity ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketEntity> updateTicket(@PathVariable Long id, @RequestBody TicketEntity ticketDetails) {
        TicketEntity updatedTicket = ticketService.updateTicket(id, ticketDetails);
        return updatedTicket != null ? ResponseEntity.ok(updatedTicket) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
