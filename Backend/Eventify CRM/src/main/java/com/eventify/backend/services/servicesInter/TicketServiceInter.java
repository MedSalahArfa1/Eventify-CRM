package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.TicketEntity;

import java.util.List;

public interface TicketServiceInter {
    List<TicketEntity> getAllTickets();

    TicketEntity getTicketById(Long id);

    TicketEntity createTicket(TicketEntity ticket);

    TicketEntity updateTicket(Long id, TicketEntity ticketDetails);

    void deleteTicket(Long id);
}
