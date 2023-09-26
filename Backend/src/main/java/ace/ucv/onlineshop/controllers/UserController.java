package ace.ucv.onlineshop.controllers;

import ace.ucv.onlineshop.dtos.JwtRequest;
import ace.ucv.onlineshop.dtos.JwtResponse;
import ace.ucv.onlineshop.dtos.ProfileDto;
import ace.ucv.onlineshop.dtos.RegistrationDto;
import ace.ucv.onlineshop.services.UserService;
import org.springframework.web.bind.annotation.*;

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
