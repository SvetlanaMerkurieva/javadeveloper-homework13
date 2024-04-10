package org.example.services.ticket;

import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudServiceImpl implements TicketCrudService {
    private static final String GET_ALL_TICKETS_QUERY = "from Ticket";
    @Override
    public Ticket createTicket(Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(ticket);
                transaction.commit();

            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return ticket;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(ticket);
                transaction.commit();
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_TICKETS_QUERY, Ticket.class).list();
        }
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Ticket.class, ticketId);
        }
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, ticketId);
            session.remove(existing);
            transaction.commit();
        }
    }
}
