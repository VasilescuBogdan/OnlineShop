package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.ClientDto;
import ace.ucv.onlineshop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public void createClient(@RequestBody ClientDto newClient){
        userService.createClient(newClient);
    }
}
