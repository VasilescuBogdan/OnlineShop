package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.ClientDto;
import ace.ucv.onlineshop.Model.Profile;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.ProfileRepository;
import ace.ucv.onlineshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProfileRepository profileRepository;

    public void createClient(ClientDto client){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User newUser = new User();

        newUser.setEmail(client.getEmail());
        newUser.setPassword(passwordEncoder.encode(client.getPassword()));
        newUser.setRole("USER");

        Profile newProfile = new Profile();
        newProfile.setFirstName(client.getFirstName());
        newProfile.setLastName(client.getLastName());
        newProfile.setNumber(client.getNumber());
        newProfile.setPoints(250);
        newProfile.setCreationDate(LocalDateTime.now());
        newProfile.setUser(newUser);

        userRepository.save(newUser);
        profileRepository.save(newProfile);
    }

}
