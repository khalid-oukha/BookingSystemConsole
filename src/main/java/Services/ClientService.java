package Services;

import Entities.Client;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryImpl;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public void addClient(Client client) {
        clientRepository.addClient(client);
    }

    public Client findClientByCin(String cin) {
        return clientRepository.findByCin(cin);
    }
}
