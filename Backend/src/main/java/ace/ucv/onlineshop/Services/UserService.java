package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.JwtRequest;
import ace.ucv.onlineshop.Dtos.JwtResponse;
import ace.ucv.onlineshop.Dtos.ProfileDto;
import ace.ucv.onlineshop.Dtos.RegistrationDto;
import ace.ucv.onlineshop.Model.Profile;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.ProfileRepository;
import ace.ucv.onlineshop.Repositories.UserRepository;
import ace.ucv.onlineshop.Security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final JwtUtil jwtUtil;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public void createClient(RegistrationDto client){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User();

        newUser.setPassword(passwordEncoder.encode(client.getPassword()));
        newUser.setUsername(client.getUsername());
        newUser.setRole("CLIENT");

        Profile newProfile = new Profile();
        newProfile.setFirstName(client.getFirstName());
        newProfile.setLastName(client.getLastName());
        newProfile.setNumber(client.getNumber());
        newProfile.setEmail(client.getEmail());
        newProfile.setPoints(250);
        newProfile.setCreationDate(LocalDate.now());
        newProfile.setUser(newUser);

        userRepository.save(newUser);
        profileRepository.save(newProfile);
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        authenticate(username, password);

        UserDetails userDetails = jwtService.loadUserByUsername(username);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findUserByUsername(username);
        return new JwtResponse(user, newGeneratedToken);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public ProfileDto getCurrentUserProfile(Principal principal){
        String currentUsername = principal.getName();
        User currentUser = userRepository.findUserByUsername(currentUsername);
        Profile currentProfile = profileRepository.findProfileByUser(currentUser);

        ProfileDto profile = new ProfileDto();
        profile.setEmail(currentProfile.getEmail());
        profile.setFirstName(currentProfile.getFirstName());
        profile.setLastName(currentProfile.getLastName());
        profile.setCreationDate(currentProfile.getCreationDate());
        profile.setNumber(currentProfile.getNumber());
        profile.setPoints(currentProfile.getPoints());

        return profile;
    }

    public void initUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User clientUser = new User();
        clientUser.setUsername("test");
        clientUser.setPassword(passwordEncoder.encode("test"));
        clientUser.setRole("CLIENT");
        userRepository.save(clientUser);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setRole("ADMIN");
        userRepository.save(adminUser);
    }
}
