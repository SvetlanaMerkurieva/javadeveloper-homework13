package org.example;

import org.example.config.DatabaseMigration;
import org.example.entities.Client;
import org.example.hibernate.HibernateUtils;
import org.example.services.client.ClientCrudServiceImpl;
import org.hibernate.Session;

import java.util.List;

public class ClientCrudTest {
    public static void main(String[] args) {
        new DatabaseMigration().startMigration();
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

        System.out.println("Існуючи клієнти в таблиці Client:");

        List<Client> clients = session.createQuery("from Client", Client.class).list();
        clients.forEach(client -> System.out.println(client.toString() + " "));

        ClientCrudServiceImpl clientsTable = new ClientCrudServiceImpl();

        System.out.println("До нас приєднується новий клієнт - Taras Topolya");
        Client newClient = new Client();
        newClient.setName("Taras Topolya");
        clientsTable.createClient(newClient);
        System.out.println(newClient);

        System.out.println("Клієнт з id = 3: ");
        Client clientById = clientsTable.getClientById(3L);
        System.out.println(clientById);


        System.out.println("Клієнт Tetyana Mastoluk вийшла заміж і змінила прізвище)))");
        clientById.setName("Tetyana Perlovko");
        clientsTable.updateClient(clientById);
        System.out.println(clientById);

        System.out.println("Клієнт з id = 8 передумав летіти.");
        clientsTable.deleteClientById(8L);

        System.out.println("Оновлена таблиця клієнтів: ");
        List<Client> clientsAll = clientsTable.getAllClients();
        clientsAll.forEach(client -> System.out.println(client.toString() + " "));

        session.close();
    }
}