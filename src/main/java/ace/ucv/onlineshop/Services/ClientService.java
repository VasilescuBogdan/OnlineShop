package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.ClientDto;
import ace.ucv.onlineshop.Model.Client;
import ace.ucv.onlineshop.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;

    public Client createClient(ClientDto client){
        Client newClient = new Client();

        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setEmail(client.getEmail());
        newClient.setNumber(client.getNumber());
        newClient.setPoints(250);
        newClient.setCreationDate(LocalDateTime.now());
        newClient.setPassword(client.getPassword());

        return clientRepository.save(newClient);
    }

}
