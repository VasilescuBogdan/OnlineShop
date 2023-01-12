package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.ProfileDto;
import ace.ucv.onlineshop.Dtos.RegistrationDto;
import ace.ucv.onlineshop.Model.Profile;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.ProfileRepository;
import ace.ucv.onlineshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProfileRepository profileRepository;

    public void createClient(RegistrationDto client){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User newUser = new User();

        newUser.setEmail(client.getEmail());
        newUser.setPassword(passwordEncoder.encode(client.getPassword()));
        newUser.setRole("CLIENT");

        Profile newProfile = new Profile();
        newProfile.setFirstName(client.getFirstName());
        newProfile.setLastName(client.getLastName());
        newProfile.setNumber(client.getNumber());
        newProfile.setPoints(250);
        newProfile.setCreationDate(LocalDate.now());
        newProfile.setUser(newUser);

        userRepository.save(newUser);
        profileRepository.save(newProfile);
    }

    public ProfileDto getCurrentUserProfile(Principal principal){
        String currentEmail = principal.getName();
        User currentUser = userRepository.findUserByEmail(currentEmail);
        Profile currentProfile = profileRepository.findProfileByUser(currentUser);

        ProfileDto profile = new ProfileDto();
        profile.setEmail(currentUser.getEmail());
        profile.setFirstName(currentProfile.getFirstName());
        profile.setLastName(currentProfile.getLastName());
        profile.setCreationDate(currentProfile.getCreationDate());
        profile.setNumber(currentProfile.getNumber());
        profile.setPoints(currentProfile.getPoints());

        return profile;
    }

}
