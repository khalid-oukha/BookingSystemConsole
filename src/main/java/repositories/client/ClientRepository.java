package repositories.client;

import Entities.Client;

public interface ClientRepository {
    void addClient(Client client);

    Client findByCin(String cin);

}
