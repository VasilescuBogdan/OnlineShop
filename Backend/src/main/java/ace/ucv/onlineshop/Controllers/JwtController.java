package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.JwtRequest;
import ace.ucv.onlineshop.Dtos.JwtResponse;
import ace.ucv.onlineshop.Services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/Jwt")
@AllArgsConstructor
public class JwtController {

    private final JwtService jwtService;


}
