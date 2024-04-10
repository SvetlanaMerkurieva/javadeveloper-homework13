package org.example;

import org.example.config.DatabaseMigration;
import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.services.client.ClientCrudServiceImpl;
import org.example.services.planet.PlanetCrudServiceImpl;
import org.example.services.ticket.TicketCrudServiceImpl;

import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class TicketCrudTest {
    public static void main(String[] args) {
        new DatabaseMigration().startMigration();
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

        TicketCrudServiceImpl ticketsTable = new TicketCrudServiceImpl();
        ClientCrudServiceImpl clientsTable = new ClientCrudServiceImpl();
        PlanetCrudServiceImpl planetsTable = new PlanetCrudServiceImpl();

        System.out.println("Існуючи квитки в таблиці Ticket:");

        List<Ticket> tickets = ticketsTable.getAllTickets();
        tickets.forEach(ticket -> System.out.println(ticket.toString() + " "));

        System.out.println("Спроба створити новий квиток для неіснуючого клієнта - отримуємо помилку org.hibernate.PropertyValueException: not-null property references a null or transient value : org.example.entities.Ticket.client ");
        Ticket newTicket = new Ticket();
        //newTicket.setClient(clientsTable.getClientById(14L));
        //newTicket.setPlanetFrom(planetsTable.getPlanetById("SAT"));
        //newTicket.setPlanetTo(planetsTable.getPlanetById("EAR"));
        //ticketsTable.createTicket(newTicket);
        //System.out.println(newTicket);

        System.out.println("Спроба створити новий квиток для неіснуючої планети - отримуємо помилку org.hibernate.PropertyValueException: not-null property references a null or transient value : org.example.entities.Ticket..planetFrom ");
        //newTicket.setClient(clientsTable.getClientById(7L));
        //newTicket.setPlanetFrom(planetsTable.getPlanetById("PLUTO"));
        //newTicket.setPlanetTo(planetsTable.getPlanetById("EAR"));
        //ticketsTable.createTicket(newTicket);
        System.out.println(newTicket);

        System.out.println("Створення ногово квитка:");
        newTicket.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        newTicket.setClient(clientsTable.getClientById(7L));
        newTicket.setPlanetFrom(planetsTable.getPlanetById("SAT"));
        newTicket.setPlanetTo(planetsTable.getPlanetById("EAR"));
        ticketsTable.createTicket(newTicket);
        System.out.println(newTicket);

        System.out.println("Квиток з id = 3: ");
        Ticket ticketById = ticketsTable.getTicketById(3L);
        System.out.println(ticketById);

        System.out.println("Клієнт з квитком під номером 10 вирішив змінити напрямок польоту з" + " \"" + "на Марс" + "\" " + " на напрямок " + "\"" + "на Меркурий" + "\"");
        Ticket newTicketByID = ticketsTable.getTicketById(10L);
        newTicketByID.setPlanetTo(planetsTable.getPlanetById("MERK"));
        ticketsTable.updateTicket(newTicketByID);
        System.out.println(newTicketByID);

        System.out.println("Квиток з id = 2 анульовано");
        ticketsTable.deleteTicketById(2L);

        System.out.println("Оновлена таблиця Ticket:");
        List<Ticket> ticketsAll = ticketsTable.getAllTickets();
        ticketsAll.forEach(ticket -> System.out.println(ticket.toString() + " "));

        session.close();

    }
}
