package org.example.services.client;

import org.example.entities.Client;
import org.example.hibernate.HibernateUtils;
import org.example.services.client.ClientCrudService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudServiceImpl implements ClientCrudService {
    private static final String GET_ALL_CLIENTS_QUERY = "from Client";
    @Override
    public Client createClient(Client client) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(client);
                transaction.commit();

            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(client);
                transaction.commit();
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_CLIENTS_QUERY, Client.class).list();
        }
    }

    @Override
    public Client getClientById(Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Client.class, clientId);
        }
    }

    @Override
    public void deleteClientById(Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existing = session.get(Client.class, clientId);
            session.remove(existing);
            transaction.commit();
        }
    }
}
