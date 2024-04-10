package org.example.services.ticket;

import org.example.entities.Planet;
import org.example.entities.Ticket;

import java.util.List;

public interface TicketCrudService {
    Ticket createTicket(Ticket ticket);
    Ticket  updateTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long ticketId);
    void deleteTicketById(Long ticketId);
}
