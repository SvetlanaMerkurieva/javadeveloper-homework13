package org.example.services.client;

import org.example.entities.Client;

import java.util.List;

public interface ClientCrudService {
    Client createClient(Client client);
    Client updateClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long clientId);
    void deleteClientById(Long clientId);
}
