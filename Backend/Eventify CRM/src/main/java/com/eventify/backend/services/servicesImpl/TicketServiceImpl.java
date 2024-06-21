package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.TicketEntity;
import com.eventify.backend.repositories.TicketRepository;
import com.eventify.backend.services.servicesInter.TicketServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketServiceInter {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketEntity getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public TicketEntity createTicket(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public TicketEntity updateTicket(Long id, TicketEntity ticketDetails) {
        TicketEntity ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setTicketType(ticketDetails.getTicketType());
            ticket.setPrice(ticketDetails.getPrice());
            ticket.setEvent(ticketDetails.getEvent());
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
