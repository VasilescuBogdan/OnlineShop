package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.ProfileDto;
import ace.ucv.onlineshop.Dtos.RegistrationDto;
import ace.ucv.onlineshop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public void createClient(@RequestBody RegistrationDto newClient){
        userService.createClient(newClient);
    }

    @GetMapping()
    public ProfileDto getAuthenticatedUser(Principal principal){
        return userService.getCurrentUserProfile(principal);
    }
}
