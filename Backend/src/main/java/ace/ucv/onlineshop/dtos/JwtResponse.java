package ace.ucv.onlineshop.dtos;

import ace.ucv.onlineshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;

}
