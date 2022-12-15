package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.ClientDto;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User createClient(ClientDto client){
        User newUser = new User();

        newUser.setFirstName(client.getFirstName());
        newUser.setLastName(client.getLastName());
        newUser.setEmail(client.getEmail());
        newUser.setNumber(client.getNumber());
        newUser.setPoints(250);
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setPassword(client.getPassword());

        return userRepository.save(newUser);
    }

}
