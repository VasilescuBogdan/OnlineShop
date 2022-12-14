package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.ClientDto;
import ace.ucv.onlineshop.Model.Client;
import ace.ucv.onlineshop.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/clients")
@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody ClientDto newClient){
        return clientService.createClient(newClient);
    }
}
