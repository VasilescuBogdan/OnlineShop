package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.JwtRequest;
import ace.ucv.onlineshop.Dtos.JwtResponse;
import ace.ucv.onlineshop.Dtos.ProfileDto;
import ace.ucv.onlineshop.Dtos.RegistrationDto;
import ace.ucv.onlineshop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RequestMapping("/api/users")
@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public void createClient(@RequestBody RegistrationDto newClient){
        userService.createClient(newClient);
    }

    @PostMapping("/authentication")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return userService.createJwtToken(jwtRequest);
    }

    @GetMapping("/profile")
    public ProfileDto getAuthenticatedUser(Principal principal){
        return userService.getCurrentUserProfile(principal);
    }

    @GetMapping("/forAdmin")
    public String forAdmin() {
        return "This URL is for admin only";
    }

    @GetMapping("/forClient")
    public String forClient() {
        return "This URL is for client only";
    }

}
